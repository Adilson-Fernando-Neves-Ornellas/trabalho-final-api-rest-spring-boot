package br.com.serratec.ecommerce.dto.produto;

import br.com.serratec.ecommerce.dto.categoria.CategoriaResponseDTO;

public class ProdutoResponseDTO extends ProdutoBaseDTO {
    
    private long id;
    private CategoriaResponseDTO categoria;  
    
    
    public CategoriaResponseDTO getCategoria() {
        return categoria;
    }
    public void setCategoria(CategoriaResponseDTO categoria) {
        this.categoria = categoria;
    }
    public long getIdProd() {
        return id;
    }
    public void setIdProd(long idProd) {
        this.id = idProd;
    }
}
