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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/logs")
@Api(value = "LOGS")
public class LogController {
    
    @Autowired
    private LogService logService;

    @GetMapping
    @ApiOperation(value = "RETORNA UMA LISTA COM TODOS ")
    public ResponseEntity<List<Long>> obterTodos() {
        return ResponseEntity.ok(logService.obterTodos());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "RETORNA UM EXPECIFICO PELO ID ")
    public ResponseEntity<Log> obterPorId(@PathVariable long id) {
        return ResponseEntity.ok(logService.obterPorId(id));
    }

}
