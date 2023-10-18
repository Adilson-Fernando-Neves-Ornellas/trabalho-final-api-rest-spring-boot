package br.com.serratec.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    public Produto() {
    }

    public Produto(long idProd, String nomeProd, double valorProd, int estoqueProd, boolean statusProd,
            String descricaoProd, String imgbase64Prod, Categoria categoria) {
        this.idProd = idProd;
        this.nomeProd = nomeProd;
        this.valorProd = valorProd;
        this.estoqueProd = estoqueProd;
        this.statusProd = statusProd;
        this.descricaoProd = descricaoProd;
        this.imgbase64Prod = imgbase64Prod;
        this.categoria = categoria;
    }



    public long getIdProd() {
        return idProd;
    }

    public void setIdProd(long idProd) {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
