package br.com.serratec.ecommerce.dto.pedido;

import br.com.serratec.ecommerce.enums.FormaPagamento;

public class PedidoBaseDTO {
    
    private double descontoTotal;
    private double acrescimoTotal;
    private double valorFinal;
    private FormaPagamento formaPagamento;
    private String observacao;

    public double getDescontoTotal() {
        return descontoTotal;
    }
    public void setDescontoTotal(double descontoTotal) {
        this.descontoTotal = descontoTotal;
    }
    public double getAcrescimoTotal() {
        return acrescimoTotal;
    }
    public void setAcrescimoTotal(double acrescimoTotal) {
        this.acrescimoTotal = acrescimoTotal;
    }
    public double getValorFinal() {
        return valorFinal;
    }
    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
