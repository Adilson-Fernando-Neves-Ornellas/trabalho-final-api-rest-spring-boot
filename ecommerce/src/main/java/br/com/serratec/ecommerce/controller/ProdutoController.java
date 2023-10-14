package br.com.serratec.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoServiceAction;

    @GetMapping
    public ResponseEntity<List<Produto>> obterTodos(){
        return ResponseEntity.ok(produtoServiceAction.obterTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterPorId(@PathVariable long id){
        return ResponseEntity.ok(produtoServiceAction.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<Produto> adicionar(@RequestBody Produto produto){
        Produto produtoAdicionado = produtoServiceAction.adcionar(produto);

        return ResponseEntity
            .status(201)
            .body(produtoAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> adicionar(@PathVariable long id, @RequestBody Produto produto){
        Produto produtoAtualizado = produtoServiceAction.atualizar(id, produto);

        return ResponseEntity
            .status(201)
            .body(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id){
        produtoServiceAction.deletar(id);

        return ResponseEntity
            .status(204)
            .build();
    }
}