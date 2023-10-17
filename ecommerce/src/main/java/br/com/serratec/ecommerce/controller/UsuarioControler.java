package br.com.serratec.ecommerce.controller;

import java.util.ArrayList;
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
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.model.email.Email;
import br.com.serratec.ecommerce.service.EmailService;
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
    public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody UsuarioRequestDTO usuario){
        UsuarioResponseDTO usuarioAdicionado = usuarioService.adcionar(usuario);
        testeEnvioDeEmail(usuario.getEmail());
        return ResponseEntity
            .status(201)
            .body(usuarioAdicionado);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "ATUALIZA UM NA LISTA EXPECIFICO")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable long id, @RequestBody UsuarioRequestDTO usuario){
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizar(id, usuario);

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

    // ----------------------------------------------
    // TESTE ENVIO DE EMAIL 
    @Autowired
    private EmailService emailServiceAction;

    @PostMapping("/email")
    @ApiOperation(value = "MANDA UM EMAIL TESTE")
    public ResponseEntity<?> testeEnvioDeEmail(String emailTeste ){

        List<String> destinatarios = new ArrayList<>();
        destinatarios.add(emailTeste);


        String mensagem = "<h1 style=\"text-align: center;\">EMAIL TESTE TRABALHO API GRUPO 01</h1>";

        Email email = new Email("Teste de email trabalho API Grupo 01", mensagem, "adilson.ornellas@aluno.senai.br", destinatarios);

        emailServiceAction.enviar(email);

        return ResponseEntity.status(200).body("E-mail enviado com sucesso!!!");
    }
}
