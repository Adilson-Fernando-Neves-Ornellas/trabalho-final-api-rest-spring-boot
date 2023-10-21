package br.com.serratec.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioLoginRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioLoginResponseDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioRequestDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.model.email.Email;
import br.com.serratec.ecommerce.service.EmailService;
import br.com.serratec.ecommerce.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> obterTodos() {
        return ResponseEntity.ok(usuarioService.obterTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable long id) {
        return ResponseEntity.ok(usuarioService.obterPorId(id));
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<PedidoResponseDTO> obterMeuPedidoPorId(@PathVariable long id) {
        return ResponseEntity.ok(usuarioService.obterMeuPedidoPorId(id));
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoResponseDTO>> obterTodosPedidoNoMeuId() {
        return ResponseEntity.ok(usuarioService.obterTodosPedidoNoMeuId());
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO usuarioAdicionado = usuarioService.adcionar(usuario);
        testeEnvioDeEmail("Cliente Adicionado", usuario.getEmail(),
                "Cliente " + usuario.getNmUsuario() + " adicionado com Sucesso");

        return ResponseEntity
                .status(201)
                .body(usuarioAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable long id, @RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizar(id, usuario);

        return ResponseEntity
                .status(201)
                .body(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        usuarioService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

    // ----------------------------------------------
    // ENVIO DE EMAIL
    @Autowired
    private EmailService emailServiceAction;

    public ResponseEntity<?> testeEnvioDeEmail(String assunto, String emailDestinatario, String mensagem) {

        List<String> destinatarios = new ArrayList<>();
        destinatarios.add(emailDestinatario);

        Email email = new Email(assunto, mensagem, "adilson.ornellas@aluno.senai.br", destinatarios);

        emailServiceAction.enviar(email);

        return ResponseEntity.status(200).body("E-mail enviado com sucesso!!!");
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginResponseDTO> logar(@RequestBody UsuarioLoginRequestDTO usuariologinRequest) {

        UsuarioLoginResponseDTO usuarioLogado = usuarioService.logar(usuariologinRequest.getEmail(),
                usuariologinRequest.getSenha());

        return ResponseEntity
                .status(200)
                .body(usuarioLogado);
    }
}
