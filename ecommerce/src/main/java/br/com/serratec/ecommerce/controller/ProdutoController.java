package br.com.serratec.ecommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.serratec.ecommerce.dto.produto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/produtos")
@SecurityRequirement(name = "bearer-key")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoServiceAction;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> obterTodos() {
        return ResponseEntity.ok(produtoServiceAction.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable long id) {
        return ResponseEntity.ok(produtoServiceAction.obterPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProdutoResponseDTO> adicionar(@RequestBody ProdutoRequestDTO produto) {
        ProdutoResponseDTO produtoAdicionado = produtoServiceAction.adcionar(produto);

        return ResponseEntity
                .status(201)
                .body(produtoAdicionado);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable long id, @RequestBody ProdutoRequestDTO produto) {
        ProdutoResponseDTO produtoAtualizado = produtoServiceAction.atualizar(id, produto);

        return ResponseEntity
                .status(200)
                .body(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        produtoServiceAction.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

}