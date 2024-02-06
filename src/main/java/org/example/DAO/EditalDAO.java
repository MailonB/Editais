package org.example.DAO;

import org.example.Main;
import org.example.modal.Anexos;
import org.example.modal.Edital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EditalDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static Boolean salvarEdital(Edital edital) {
        ConexaoSingleton con = ConexaoSingleton.getInstance();
        Connection Conexao = con.getConnection();

        String sql = "INSERT INTO public.edital (licitacao, edital,objeto, modalidade, tipo, tdpublicacao, portal) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = Conexao.prepareStatement(sql);
            stmt.setInt(1, edital.getLicitacao());
            stmt.setString(2, edital.getEdital());
            stmt.setString(3, edital.getObjeto());
            stmt.setString(4, edital.getModalidade());
            stmt.setString(5, edital.getTipo());
            stmt.setString(6, edital.getDtPublicacao());
            stmt.setString(7, edital.getPortal());
            stmt.execute();

            LOGGER.info("cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Adicione esta linha para imprimir o stack trace
            return false;
        }
    }

    public static List<Edital> listarEdital() {
        ConexaoSingleton con = ConexaoSingleton.getInstance();
        Connection connection = con.getConnection();
        List<Edital> edital = new ArrayList<>();

        String sql = "SELECT * FROM public.edital";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Edital editais = new Edital();

                editais.setLicitacao(rs.getInt("licitacao"));
                editais.setEdital(rs.getString("edital"));
                editais.setObjeto(rs.getString("objeto"));
                editais.setModalidade(rs.getString("modalidade"));
                editais.setTipo(rs.getString("tipo"));
                editais.setDtPublicacao(rs.getString("tdpublicacao"));
                editais.setPortal(rs.getString("portal"));
                edital.add(editais);
            }
            for (Edital edital1 : edital) {
                //   System.out.println(edital1.toString());
            }

            return edital;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void listarEditalAnexos() {
        ConexaoSingleton con = ConexaoSingleton.getInstance();
        Connection connection = con.getConnection();
        List<Edital> editais = new ArrayList<>();
        List<Anexos> ListAnexos = new ArrayList<>();

        editais = listarEdital();

        for (Edital edital1 : editais) {
            LOGGER.info("Licitação: {} Edital: {} Objeto: {} MNodalidae: {} tipo: {} Data de Publicação: {} Portal: {}",
                    edital1.getLicitacao(),
                    edital1.getEdital(),
                    edital1.getObjeto(),
                    edital1.getModalidade(),
                    edital1.getTipo(),
                    edital1.getDtPublicacao(),
                    edital1.getPortal());

            String sql = "select * from Anexo\n" +
                    "\n" +
                    "Where codedital =" + edital1.getLicitacao();

            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Anexos anexos1 = new Anexos(rs.getString("nome"), rs.getString("link"), rs.getInt("codedital"));
                    ListAnexos.add(anexos1);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (Anexos anexos : ListAnexos) {
                LOGGER.info("Nome Anexo: {} Link para Dowload: {}",
                        anexos.getNome(),
                        anexos.getLink());
            }
            ListAnexos.clear();
        }
    }

    public Boolean excluirEdital(String licitacao) {
        ConexaoSingleton Con = ConexaoSingleton.getInstance();
        Connection Conexao = Con.getConnection();

        String sql = "DELETE FROM public.Edital WHERE licitacao=?";

        try {
            PreparedStatement stmt = Conexao.prepareStatement(sql);
            stmt.setString(1, licitacao);

            int linhasAfetadas = stmt.executeUpdate(); // Usar executeUpdate para operações de DELETE

            LOGGER.info("Excluido com sucesso!");
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

