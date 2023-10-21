package br.com.serratec.ecommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.serratec.ecommerce.dto.auditoria.AuditoriaResponseDTO;
import br.com.serratec.ecommerce.service.AuditoriaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/auditorias")
@SecurityRequirement(name = "bearer-key")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AuditoriaResponseDTO>> obterTodos() {
        return ResponseEntity.ok(auditoriaService.obterAuditoria());
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AuditoriaResponseDTO> obterPorId(@PathVariable long id) {
        return ResponseEntity.ok(auditoriaService.obterPorId(id));
    }

}
