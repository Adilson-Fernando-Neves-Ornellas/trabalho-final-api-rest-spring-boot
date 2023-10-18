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

    public long getIdCateforia() {
        return idCategoria;
    }

    public void setIdCateforia(long idCateforia) {
        this.idCategoria = idCateforia;
    }
    

}
