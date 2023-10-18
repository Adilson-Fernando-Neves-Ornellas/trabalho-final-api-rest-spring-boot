package br.com.serratec.ecommerce.dto.pedido;

import java.util.Date;

import br.com.serratec.ecommerce.enums.FormaPagamento;

public class PedidoBaseDTO {
    
    // private long id;
    private double descontoTotal;
    private double acrescimoTotal;
    private double valorFinal;
    // private Date dataCompra;
    private FormaPagamento formaPagamento;

    // public long getId() {
    //     return id;
    // }
    // public void setId(long id) {
    //     this.id = id;
    // }
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
    // public Date getDataCompra() {
    //     return dataCompra;
    // }
    // public void setDataCompra() {
    //     this.dataCompra = new Date();
    // }
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}
