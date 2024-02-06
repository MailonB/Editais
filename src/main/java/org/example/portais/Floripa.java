package org.example.portais;

import com.google.gson.JsonObject;

public class Floripa {



    public static String getNomePortal(){
        return "Prefeitura Municipal de Florianópolis";
    }



    public static JsonObject getParamDetalhe (int processo){
        JsonObject detalhe = new JsonObject();
        JsonObject itens = new JsonObject();
        JsonObject idioma = new JsonObject();


        itens.addProperty("nCdProcesso", processo);
        itens.addProperty("nCdModulo", 18);
        itens.addProperty("nCdSituacao", 1);
        itens.addProperty("sNrProcesso", "");
        itens.addProperty("nCdEdital", processo);
        itens.addProperty("tmpTipoMuralProcesso", 2);
        idioma.addProperty("nCdIdioma", 1);

        detalhe.add("dtoProcesso", itens);

        return detalhe;
    }


    public static JsonObject getParams () {
        JsonObject processo = new JsonObject();
        JsonObject itens = new JsonObject();
        JsonObject paginacao = new JsonObject();
        JsonObject idioma = new JsonObject();

        itens.addProperty("nAnoFinalizacao", 0);
        itens.addProperty("tmpTipoMuralProcesso", 2);
        itens.addProperty("nCdModulo", 0);
        itens.addProperty("nCdModalidadeFase", 0);
        itens.addProperty("nCdTipoModalidade", 0);
        itens.addProperty("tmpTipoMuralVisao", 0);
        itens.addProperty("nCdSituacao", 0);
        itens.addProperty("nCdTipoProcesso", 0);
        itens.addProperty("nCdEmpresa", 0);
        itens.addProperty("sNrProcesso", "");
        itens.addProperty("nCdProcesso", 0);
        itens.addProperty("sDsObjeto", "");
        itens.addProperty("sDtPeriodoDe", "");
        itens.addProperty("sDtPeriodoAte", "");
        itens.addProperty("sOrdenarPor", "TDTINICIAL");
        itens.addProperty("sOrdenarPorDirecao", "DESC");

        paginacao.addProperty("nPaginaDe", 1);
        paginacao.addProperty("nPaginaAte", 50);
        idioma.addProperty("nCdIdioma", 1);

        itens.add("dtoPaginacao", paginacao);
        itens.add("dtoIdioma", idioma);


        processo.add("dtoProcesso", itens);
        return processo;
    }

    public static JsonObject getParamsAnexos (int nCdAnexo, int nCdModulo, int nCdOrigem){
        JsonObject dtoAnexo = new JsonObject();
        JsonObject anexo = new JsonObject();

        dtoAnexo.addProperty("nCdAnexo",nCdAnexo);
        dtoAnexo.addProperty("sNmLocalAnexo","EditalAnexos");
        dtoAnexo.addProperty("nCdModulo",nCdModulo);
        dtoAnexo.addProperty("nCdOrigem",nCdOrigem);
        dtoAnexo.addProperty( "bFlPublico",1);

        anexo.add("dtoAnexo", dtoAnexo);

        return anexo;
    }

}
