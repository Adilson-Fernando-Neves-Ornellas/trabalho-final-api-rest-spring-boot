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

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

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
    public PedidoResponseDTO adicionar(@RequestBody PedidoRequestDTO pedido) {
        return pedidoService.savePedido(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizar(@PathVariable Long id, @RequestBody PedidoRequestDTO pedidoRequest) {
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
}      
