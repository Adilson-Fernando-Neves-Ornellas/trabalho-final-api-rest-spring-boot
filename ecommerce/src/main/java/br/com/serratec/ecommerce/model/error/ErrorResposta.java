package br.com.serratec.ecommerce.model.error;

public class ErrorResposta {
    private int status;
    private String mensagem;
    private String titulo;
    private String dataHora;

    public ErrorResposta(int status, String mensagem, String titulo, String dataHora) {
        this.status = status;
        this.mensagem = mensagem;
        this.titulo = titulo;
        this.dataHora = dataHora;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
