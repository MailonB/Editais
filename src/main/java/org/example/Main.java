package org.example;


import org.example.DAO.EditalDAO;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

     //   inicia a captura no portal
        Captura captura = new Captura();
        captura.iniciaCaptura();

        // imprime os Editais + os anexos
        EditalDAO editalDAO = new EditalDAO();
        editalDAO.listarEditalAnexos();
    }
}