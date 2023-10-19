package br.com.serratec.ecommerce.dto.produto;

public class ProdutoCategoriaDTO extends ProdutoBaseDTO {
    
    private long id;
    private long idCategoria;  

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }
}
