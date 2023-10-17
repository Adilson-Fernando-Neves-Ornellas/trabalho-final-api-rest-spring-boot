package br.com.serratec.ecommerce.dto.produto;

public abstract class ProdutoBaseDTO {

    private long idProd;
    private String nomeProd;
    private double valorProd;
    private int estoqueProd;
    private String descricaoProd;
    private String imgbase64Prod;
    private String nomeFile;
    private String pathFile;
    private Long idPedidoItens;// remover depois

    public Long getIdPedidoItens() {
        return idPedidoItens;
    }

    public void setIdPedidoItens(Long idPedidoItens) {
        this.idPedidoItens = idPedidoItens;
    }

    public long getId() {
        return idProd;
    }

    public void setId(long idProd) {
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

}
