package br.com.serratec.ecommerce.dto.produto;

public abstract class ProdutoBaseDTO {

    private String nomeProd;
    private double valorProd;
    private int estoqueProd;
    private String descricaoProd;
    private String imgbase64Prod;
    private boolean statusProd;
    

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

    public boolean getStatusProd() {
        return statusProd;
    }

    public void setStatusProd(boolean statusProd) {
        this.statusProd = statusProd;
    }

}
