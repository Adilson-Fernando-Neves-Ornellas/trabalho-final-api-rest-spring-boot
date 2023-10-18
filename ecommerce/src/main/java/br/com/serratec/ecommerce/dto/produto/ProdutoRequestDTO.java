package br.com.serratec.ecommerce.dto.produto;

import br.com.serratec.ecommerce.dto.categoria.CategoriaRequestDTO;

public class ProdutoRequestDTO extends ProdutoBaseDTO {

    private boolean statusProd;
    private CategoriaRequestDTO categoria;

    public CategoriaRequestDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaRequestDTO categoria) {
        this.categoria = categoria;
    }

    public boolean isStatusProd() {
        return statusProd;
    }

    public void setStatusProd(boolean statusProd) {
        this.statusProd = statusProd;
    }

}
