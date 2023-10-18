package br.com.serratec.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class PedidoItens {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedidoItens")
    private long idPedidoItens;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JsonBackReference
    private Produto produto;

    private double descontoProd;

    private double acrescimoProd;

    @Column(nullable = false)
    private int quantidade;

    private double valorTotal;   

    public PedidoItens(long idPedidoItens, Produto produto, double descontoProd, double acrescimoProd,
            int quantidade, double valorTotal) {
        this.idPedidoItens = idPedidoItens;
        this.produto = produto;
        this.descontoProd = descontoProd;
        this.acrescimoProd = acrescimoProd;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public PedidoItens(){}

    public long getIdPedidoItens() {
        return idPedidoItens;
    }

    public void setIdPedidoItens(long idPedidoItens) {
        this.idPedidoItens = idPedidoItens;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getDescProduto() {
        return descontoProd;
    }

    public void setDescProduto(double descontoProd) {
        this.descontoProd = descontoProd;
    }

    public double getAcresProduto() {
        return acrescimoProd;
    }

    public void setAcresProduto(double acrescimoProd) {
        this.acrescimoProd = acrescimoProd;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    
}
