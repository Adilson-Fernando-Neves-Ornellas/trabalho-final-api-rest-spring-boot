package br.com.serratec.ecommerce.dto.produto;

public class ProdutoRequestDTO extends ProdutoBaseDTO {

    private long id;
    private long idCategoria;

    public long getIdProd() {
        return id;
    }

    public void setIdProd(long idProd) {
        this.id = idProd;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }
    

}
