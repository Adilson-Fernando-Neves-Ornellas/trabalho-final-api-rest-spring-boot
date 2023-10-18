package br.com.serratec.ecommerce.dto.categoria;

import java.util.List;

import br.com.serratec.ecommerce.dto.produto.ProdutoResponseDTO;

public class CategoriaResponseDTO {

    private long idCategoria;
    private String nmCategoria;
    private String descricao;
    private List<ProdutoResponseDTO> produtos;


    public CategoriaResponseDTO(){
        
    }

    public long getIdCategoria() {
        return idCategoria;
    }


    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
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

    public List<ProdutoResponseDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoResponseDTO> produtos) {
        this.produtos = produtos;
    }

}
