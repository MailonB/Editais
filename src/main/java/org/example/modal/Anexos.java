package org.example.modal;

public class Anexos {

    private String nome;
    private String link;
    private int codEdital;


    public Anexos(String nome, String link, int codEdital) {
        this.nome = nome;
        this.link = link;
        this.codEdital = codEdital;

    }

    public int getCodEdital() {
        return codEdital;
    }

    public void setCodEdital(int codEdital) {
        this.codEdital = codEdital;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLink() {
        String texto = "https://wbc.pmf.sc.gov.br/portal/Download.aspx" + link;
        return texto;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Anexos{" +
                "nome='" + nome + '\'' +
                ", link='" + link + '\'' +
                ", codEdital=" + codEdital +
                '}';
    }
}
