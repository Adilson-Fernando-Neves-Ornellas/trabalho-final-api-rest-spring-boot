package br.com.serratec.ecommerce.dto.categoria;

import javax.persistence.Column;

import br.com.serratec.ecommerce.model.Categoria;

public class CategoriaRequestDTO {
      
    private String nmCategoria;

    
    private String descricao;


    public CategoriaRequestDTO (){

    }
    public String getNmCategoria() {
        return nmCategoria;
    }

    public void setNmCategoria(String nmCategoria) {
        this.nmCategoria = nmCategoria;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
