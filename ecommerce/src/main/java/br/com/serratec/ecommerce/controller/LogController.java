package br.com.serratec.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.model.Log;
import br.com.serratec.ecommerce.service.LogService;



@RestController
@RequestMapping("/logs")
public class LogController {
    
    @Autowired
    private LogService logService;

    @GetMapping
    public ResponseEntity<List<Long>> obterTodos() {
        return ResponseEntity.ok(logService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> obterPorId(@PathVariable long id) {
        return ResponseEntity.ok(logService.obterPorId(id));
    }

}
