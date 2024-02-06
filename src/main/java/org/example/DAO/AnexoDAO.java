package org.example.DAO;

import org.example.Main;
import org.example.modal.Anexos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AnexoDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static Boolean salvarAnexo(Anexos anexo) {
        ConexaoSingleton con = ConexaoSingleton.getInstance();
        Connection Conexao =  con.getConnection();

        String sql ="INSERT INTO public.anexo (nome, link,codEdital) VALUES (?,?,?)";

        try {
            PreparedStatement stmt = Conexao.prepareStatement(sql);
            stmt.setString(1, anexo.getNome());
            stmt.setString(2, anexo.getLink());
            stmt.setInt(3, anexo.getCodEdital());

            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Adicione esta linha para imprimir o stack trace
            return false;
        }
    }

    public Boolean excluirAnexo(int codEdital) {
        ConexaoSingleton Con = ConexaoSingleton.getInstance();
        Connection Conexao = Con.getConnection();

        String sql = "DELETE FROM public.Anexo WHERE codeedital=?";

        try {
            PreparedStatement stmt = Conexao.prepareStatement(sql);
            stmt.setInt(1, codEdital);

            int linhasAfetadas = stmt.executeUpdate(); // Usar executeUpdate para operações de DELETE

            LOGGER.info("Excluido com sucesso!");
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
