package org.example;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.DAO.AnexoDAO;
import org.example.DAO.EditalDAO;
import org.example.modal.Anexos;
import org.example.modal.Edital;
import org.example.portais.Floripa;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.example.portais.Floripa.*;
import static org.example.util.util.*;

public class Captura {



    public static void iniciaCaptura() throws IOException {

           // carregar html principal
        String urlHome = "https://wbc.pmf.sc.gov.br/Portal/Mural.aspx?nNmTela=E";
        HttpRequester client = new HttpRequester();
        HttpResponse<String> response = client.get(urlHome);

        // fazer o parser do html
        Document document = toParser(response.body());

        // requisita os processos
        String urlPesquisa = "https://wbc.pmf.sc.gov.br/Portal/WebService/Servicos.asmx/PesquisarProcessosPorSituacoesAgrupadas";
        response = client.post(urlPesquisa, getParams());

        // fazer o parser do json
        JsonObject processos = toJSON(response.body());

        // iterar os processos e requisitar os anexos
        for (JsonElement element : processos.get("d").getAsJsonArray()) {
            JsonObject editais = element.getAsJsonObject();

            // requisitar os detalhes do edital
            String urlDetalhe = "https://wbc.pmf.sc.gov.br/portal/WebService/Servicos.asmx/PesquisarProcessoDetalhes";
            response = client.post(urlDetalhe, getParamDetalhe(editais.get("nCdProcesso").getAsInt()));

            JsonObject processosDet = toJSON(response.body());
            JsonObject editalDet = processosDet.get("d").getAsJsonObject();

            Edital edital = new Edital();

            edital.setLicitacao(editalDet.get("nCdProcesso").getAsInt());
            edital.setEdital(editalDet.get("sNrEdital").getAsString());
            edital.setObjeto(editalDet.get("sDsObjeto").getAsString());
            edital.setModalidade(editalDet.get("sNmModalidade").getAsString());
            edital.setTipo(editalDet.get("sNmModalidadeTipo").getAsString());
            edital.setDtPublicacao(dateToString(editalDet.get("tDtPublicacao").getAsString()));
            edital.setPortal(Floripa.getNomePortal());

                EditalDAO.salvarEdital(edital);

                EditalDAO.listarEdital();

            // Buscar os anexos de cada edital
            // tem o parametro nCdEdital: 981 que tem que ser mandado na requisição

            String urlAnexos = "https://wbc.pmf.sc.gov.br/portal/WebService/Servicos.asmx/PesquisarAnexos";
            response = client.post(urlAnexos, getParamsAnexos(editalDet.get("nCdAnexo").getAsInt(),
                    editalDet.get("nCdModulo").getAsInt(),
                    editalDet.get("nCdOrigem").getAsInt()));


            JsonObject anexo = toJSON(response.body());

            for (JsonElement elementAnexo : anexo.get("d").getAsJsonArray()) {
                JsonObject anexos = elementAnexo.getAsJsonObject();

                //Criando o anexo e enviando pro banco
                Anexos ane = new Anexos(anexos.get("sNmArquivo").getAsString(),anexos.get("sDsParametroCriptografado").getAsString(),editalDet.get("nCdProcesso").getAsInt());

                AnexoDAO anexoDAO = new AnexoDAO();
                anexoDAO.salvarAnexo(ane);

            }
        }

    }
}