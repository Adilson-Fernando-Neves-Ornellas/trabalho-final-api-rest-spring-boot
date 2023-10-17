package br.com.serratec.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduto")
    private long idProd;

    @Column(nullable = false)
    private String nomeProd;

    @Column(nullable = false)
    private double valorProd;

    @Column(nullable = false)
    private int estoqueProd;

    @Column(nullable = false)
    private boolean statusProd;

    @Column(nullable = false)
    private String descricaoProd;


    private String imgbase64Prod;

    private String nomeFile;

    private String pathFile;

    // @OneToOne(mappedBy = "idCategoria")
    // private Categoria categoria;

    @OneToOne(mappedBy = "produto")
    private PedidoItens pedidoItem;

    // constructors
    public Produto() {
    }
    
    public Produto(
        Long idProd,
        String nomeProd, 
        double valorProd, 
        int estoqueProd, 
        boolean statusProd,
        String descricaoProd, 
        String imgbase64Prod,
        String nomeFile,
        String pathFile
        // Categoria categoria
        ) {
        this.idProd = idProd;
        this.nomeProd = nomeProd;
        this.valorProd = valorProd;
        this.estoqueProd = estoqueProd;
        this.statusProd = statusProd;
        this.descricaoProd = descricaoProd;
        this.imgbase64Prod = imgbase64Prod;
        this.nomeFile = nomeFile;
        this.pathFile = pathFile;
        // this.categoria = categoria;
    }


    // getters and setters
    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public double getValorProd() {
        return valorProd;
    }

    public void setValorProd(double valorProd) {
        this.valorProd = valorProd;
    }

    public int getEstoqueProd() {
        return estoqueProd;
    }

    public void setEstoqueProd(int estoqueProd) {
        this.estoqueProd = estoqueProd;
    }

    public boolean isStatusProd() {
        return statusProd;
    }

    public void setStatusProd(boolean statusProd) {
        this.statusProd = statusProd;
    }

    public String getDescricaoProd() {
        return descricaoProd;
    }

    public void setDescricaoProd(String descricaoProd) {
        this.descricaoProd = descricaoProd;
    }

    public String getImgbase64Prod() {
        return imgbase64Prod;
    }

    public void setImgbase64Prod(String imgbase64Prod) {
        this.imgbase64Prod = imgbase64Prod;
    }
    
    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
       this.pathFile = pathFile;
    }

    public void setIdProd(long idProd) {
        this.idProd = idProd;
    }

    public PedidoItens getPedidoItem() {
        return pedidoItem;
    }

    public void setPedidoItem(PedidoItens pedidoItem) {
        this.pedidoItem = pedidoItem;
    }

    // public Categoria getCategoria() {
    //     return categoria;
    // }

    // public void setCategoria(Categoria categoria) {
    //     this.categoria = categoria;
    // }
    
}
