package br.com.serratec.ecommerce.controller;

import java.util.ArrayList;
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
import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.com.serratec.ecommerce.enums.FormaPagamento;
import br.com.serratec.ecommerce.model.PedidoItens;
import br.com.serratec.ecommerce.model.email.Email;
import br.com.serratec.ecommerce.service.EmailService;
import br.com.serratec.ecommerce.service.PedidoService;
import br.com.serratec.ecommerce.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/pedidos")
@SecurityRequirement(name = "bearer-key")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PedidoResponseDTO>> obterTodos() {
        return ResponseEntity.ok(pedidoService.obterTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PedidoResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> adicionar(@RequestBody PedidoRequestDTO pedido) {
        UsuarioResponseDTO usuarioResponse = usuarioService.obterPorId(pedido.getIdUsuario());

        PedidoResponseDTO pedidoAdicionado = pedidoService.savePedido(pedido);
        EnvioDeEmail("Pedido Realizado com sucesso!", usuarioResponse.getEmail(), pedidoAdicionado.getDescontoTotal(),
                pedidoAdicionado.getAcrescimoTotal(), pedidoAdicionado.getValorFinal(),
                pedidoAdicionado.getFormaPagamento(), pedidoAdicionado.getObservacao(), pedido.getPedidoItens(),
                "Cliente " + usuarioResponse.getNmUsuario() + ", seu pedido de numero " + pedidoAdicionado.getId()
                        + " foi adicionado com Sucesso");

        return ResponseEntity
                .status(201)
                .body(pedidoAdicionado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizar(@PathVariable Long id,
            @RequestBody PedidoRequestDTO pedidoRequest) {
        PedidoResponseDTO pedidoAtualizado = pedidoService.atualizar(id, pedidoRequest);

        return ResponseEntity
                .status(200)
                .body(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        pedidoService.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

    // ----------------------------------------------
    // ENVIO DE EMAIL
    @Autowired
    private EmailService emailServiceAction;

    public ResponseEntity<?> EnvioDeEmail(String assunto, String emailDestinatario, double descontoTotal,
            double acrescimoTotal, double valorFinal, FormaPagamento formaPagamento, String observacao,
            List<PedidoItens> pedidoItens, String titulo) {
        // Construa o corpo do email em formato HTML
        String mensagem = "<html><head><title>" + assunto + "</title></head><body>";

        // Título
        mensagem += "<h1>" + titulo + "</h1>";

        // Lista de Itens do Pedido
        mensagem += "<h2>Itens do Pedido</h2>";
        mensagem += "<ul>";
        for (PedidoItens item : pedidoItens) {
            mensagem += "<li>" +
                    "Id do Produto: " + item.getIdPedidoItens() + "<br>" +
                    "Acrescimo do Produto: " + item.getAcresProduto() + "<br>" +
                    "Desconto do produto: " + item.getDescProduto() + "<br>" +
                    "Quantidade: " + item.getQuantidade() + " unidades" + "<br>" +
                    "Valor unitario do produto: " + item.getVlUnitario() + " unidades" + "<br>" +
                    "Valor total dos produtos: " + item.getValorTotal() + "<br>" +
                    "</li>" + "<br>";
        }
        mensagem += "</ul>";

        // Informações do pedido
        mensagem += "<p>Forma de Pagamento: " + formaPagamento + "</p>";
        mensagem += "<p>Acrescimo Total: " + acrescimoTotal + "</p>";
        mensagem += "<p>Desconto Total: " + descontoTotal + "</p>";
        mensagem += "<p>Observação: " + observacao + "</p>";
        mensagem += "<p>Valor Final: " + valorFinal + "</p>";

        mensagem += "</body></html>";

        List<String> destinatarios = new ArrayList<>();
        destinatarios.add(emailDestinatario);

        Email email = new Email(assunto, mensagem, "adilson.ornellas@aluno.senai.br", destinatarios);

        emailServiceAction.enviar(email);

        return ResponseEntity.status(200).body("E-mail enviado com sucesso!!!");
    }

}
