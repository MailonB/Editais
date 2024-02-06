package org.example.modal;


public class Edital {

    private int licitacao;
    private String Edital;
    private String Objeto;
    private String modalidade;
    private String tipo;
    private String dtPublicacao;
    private String portal;

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public int getLicitacao() {
        return licitacao;
    }

    public void setLicitacao(int licitacao) {
        this.licitacao = licitacao;
    }

    public String getEdital() {
        return Edital;
    }

    public void setEdital(String edital) {
        Edital = edital;
    }

    public String getObjeto() {
        return Objeto;
    }

    public void setObjeto(String objeto) {
        Objeto = objeto;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDtPublicacao() {
        String texto = ""+dtPublicacao;
        return texto;
    }

    public void setDtPublicacao(String dtPublicacao) {
        this.dtPublicacao = dtPublicacao;
    }

    @Override
    public String toString() {
        return "Edital{" +
                "licitacao=" + licitacao +
                ", Edital='" + Edital + '\'' +
                ", Objeto='" + Objeto + '\'' +
                ", modalidade='" + modalidade + '\'' +
                ", tipo='" + tipo + '\'' +
                ", dtPublicacao='" + dtPublicacao + '\'' +
                ", portal='" + portal + '\'' +
                '}';
    }
}
