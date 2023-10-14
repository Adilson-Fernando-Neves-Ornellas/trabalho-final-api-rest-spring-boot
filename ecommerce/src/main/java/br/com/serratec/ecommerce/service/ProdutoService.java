package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepositoryAction;

    public List<Produto> obterTodos(){
        return produtoRepositoryAction.findAll();
    }

    public Produto obterPorId(Long id){
        Optional<Produto> optProduto = produtoRepositoryAction.findById(id);

        if(optProduto.isEmpty()){
            throw new RuntimeException("Nenhum registro encontardo para o id: "+ id);
        }
        return optProduto.get();
    }

    public Produto adcionar(Produto Produto){
        Produto.setIdProd(0l);        
        Produto = produtoRepositoryAction.save(Produto);
        return Produto;
    }

    public Produto atualizar(Long id, Produto Produto){
        obterPorId(id);
        Produto.setIdProd(id);
        return produtoRepositoryAction.save(Produto);
    }

    public void deletar(long id){
        obterPorId(id);
        produtoRepositoryAction.deleteById(id);
    }
    
}
