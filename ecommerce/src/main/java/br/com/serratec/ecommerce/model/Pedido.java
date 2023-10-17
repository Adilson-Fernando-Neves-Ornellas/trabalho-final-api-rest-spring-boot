package br.com.serratec.ecommerce.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.serratec.ecommerce.enums.FormaPagamento;

@Entity
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido")
    private long idPedido;

    private double descontoTotal;

    private double acrescimoTotal;

    @Column(nullable = false)
    private double valorFinal;

    @Column(nullable = false)
    private Date dataCompra;

    @Column(nullable = false)
    private FormaPagamento formaPagamento;
 
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoItens> itens = new ArrayList<>();

    public void adicionarItem(PedidoItens item) {
        itens.add(item);
        item.setPedido(this);
    }

    public Pedido() {
        this.dataCompra = new Date();
    }

    
    public Pedido(long idPedido, double descontoTotal, double acrescimoTotal, double valorFinal,
            FormaPagamento formaPagamento, Usuario usuario, List<PedidoItens> itens) {
        this.idPedido = idPedido;
        this.descontoTotal = descontoTotal;
        this.acrescimoTotal = acrescimoTotal;
        this.valorFinal = valorFinal;
        this.dataCompra = new Date();
        this.formaPagamento = formaPagamento;
        this.usuario = usuario;
        this.itens = itens;
    }


    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

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

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<PedidoItens> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItens> itens) {
        this.itens = itens;
    }

    
}
