package br.com.serratec.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dto.categoria.CategoriaRequestDTO;
import br.com.serratec.ecommerce.dto.categoria.CategoriaResponseDTO;
import br.com.serratec.ecommerce.service.CategoriaService;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/categoria")
@Api(value = "CATEGORIAS")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaResponseDTO> obterTodos(){
        return categoriaService.obterCategoria();
    }
    
    @GetMapping(value="/{id}")
    public CategoriaResponseDTO obterPorId (@PathVariable long id) {
        return categoriaService.obterPorId(id);
    }

    @PostMapping
    public CategoriaResponseDTO adicionar(@RequestBody CategoriaRequestDTO categoriaDTO){
        return categoriaService.adicionar(categoriaDTO);
    }

    @PutMapping(value="/{id}")
    public CategoriaResponseDTO atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaDTO){
        return categoriaService.atualizar(id, categoriaDTO);

    }

    @DeleteMapping(value ="/{id}")
    public void delete(@PathVariable Long id){
        categoriaService.deletar(id);

    }
    


    
}
