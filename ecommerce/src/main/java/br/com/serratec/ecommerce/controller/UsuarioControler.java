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

import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(value = "USUARIOS")
public class UsuarioControler {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @ApiOperation(value = "RETORNA UMA LISTA COM TODOS ")
    public ResponseEntity<List<UsuarioResponseDTO>> obterTodos(){
        return ResponseEntity.ok(usuarioService.obterTodos());
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "RETORNA UM EXPECIFICO PELO ID ")
    public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable long id){
        return ResponseEntity.ok(usuarioService.obterPorId(id));
    }

    @PostMapping
    @ApiOperation(value = "ADICIONA MAIS UM NA LISTA ")
    public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody UsuarioRequestDTO usuarioRequest){
        UsuarioResponseDTO usuarioAdicionado = usuarioService.adcionar(usuarioRequest);

        return ResponseEntity
            .status(201)
            .body(usuarioAdicionado);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "ATUALIZA UM NA LISTA EXPECIFICO")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable long id, @RequestBody UsuarioRequestDTO usuarioRequest){
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizar(id, usuarioRequest);

        return ResponseEntity
            .status(201)
            .body(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "DELETA UM NA LISTA EXPECIFICO")
    public ResponseEntity<?> deletar(@PathVariable long id){
        usuarioService.deletar(id);

        return ResponseEntity
            .status(204)
            .build();
    }
}
