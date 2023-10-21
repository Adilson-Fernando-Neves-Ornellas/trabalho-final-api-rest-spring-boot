package br.com.serratec.ecommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.serratec.ecommerce.dto.categoria.CategoriaRequestDTO;
import br.com.serratec.ecommerce.dto.categoria.CategoriaResponseDTO;
import br.com.serratec.ecommerce.service.CategoriaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/categorias")
@SecurityRequirement(name = "bearer-key")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> obterTodos() {
        return ResponseEntity.ok(categoriaService.obterCategoria());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriaResponseDTO> obterPorId(@PathVariable long id) {
        return ResponseEntity.ok(categoriaService.obterPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoriaResponseDTO> adicionar(@RequestBody CategoriaRequestDTO categoriaDTO) {
        return ResponseEntity.status(201)
                .body(categoriaService.adicionar(categoriaDTO));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id,
            @RequestBody CategoriaRequestDTO categoriaDTO) {
        return ResponseEntity.status(200)
                .body(categoriaService.atualizar(id, categoriaDTO));

    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.status(204).build();
    }

}
