package br.com.serratec.ecommerce.model.email;

import java.util.List;

public class Email {
    
    private String assunto;
    private String mensagem;
    private String remetente;
    private List<String> destinatarios;

    // constructors
    public Email(String assunto, String mensagem, String remetente, List<String> destinatarios) {
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.remetente = remetente;
        this.destinatarios = destinatarios;
    }

    public Email() {
    }
    
    // get and set
    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public List<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
    }    
}
