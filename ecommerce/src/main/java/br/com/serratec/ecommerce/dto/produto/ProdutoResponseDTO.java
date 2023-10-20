package br.com.serratec.ecommerce.dto.produto;

import br.com.serratec.ecommerce.dto.categoria.CategoriaProdutoDTO;

public class ProdutoResponseDTO extends ProdutoBaseDTO {
    
    private long id;
    private CategoriaProdutoDTO categoria;  
    
    public CategoriaProdutoDTO getCategoria() {
        return categoria;
    }
    public void setCategoria(CategoriaProdutoDTO categoria) {
        this.categoria = categoria;
    }
    public long getIdProd() {
        return id;
    }
    public void setIdProd(long idProd) {
        this.id = idProd;
    }
}
