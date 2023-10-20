package br.com.serratec.ecommerce.dto.categoria;

public class CategoriaRequestDTO {
      
    private String nmCategoria;
    private String descricao;
    private Boolean statusCate;

    public CategoriaRequestDTO (){

    }

    public Boolean getStatusCate() {
        return statusCate;
    }
    public void setStatusCate(Boolean statusCate) {
        this.statusCate = statusCate;
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
