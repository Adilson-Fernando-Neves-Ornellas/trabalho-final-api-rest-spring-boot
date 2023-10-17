package br.com.serratec.ecommerce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.ecommerce.dto.produto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/produtos")
@Api(value = "PRODUTOS")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoServiceAction;

    @GetMapping
    @ApiOperation(value = "RETORNA UMA LISTA COM TODOS ")
    public ResponseEntity<List<ProdutoResponseDTO>> obterTodos() {
        return ResponseEntity.ok(produtoServiceAction.obterTodos());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "RETORNA UM EXPECIFICO PELO ID ")
    public ResponseEntity<ProdutoResponseDTO> obterPorId(@PathVariable long id) {
        return ResponseEntity.ok(produtoServiceAction.obterPorId(id));
    }

    @PostMapping
    @ApiOperation(value = "ADICIONA MAIS UM NA LISTA ")
    public ResponseEntity<ProdutoResponseDTO> adicionar(@RequestBody ProdutoRequestDTO produto) {
        ProdutoResponseDTO produtoAdicionado = produtoServiceAction.adcionar(produto);

        return ResponseEntity
                .status(201)
                .body(produtoAdicionado);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "ATUALIZA UM NA LISTA EXPECIFICO")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable long id, @RequestBody ProdutoRequestDTO produto) {
        ProdutoResponseDTO produtoAtualizado = produtoServiceAction.atualizar(id, produto);

        return ResponseEntity
                .status(200)
                .body(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "DELETA UM NA LISTA EXPECIFICO")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        produtoServiceAction.deletar(id);

        return ResponseEntity
                .status(204)
                .build();
    }

    // -----------------------------------------------------------------------------------

    // Define uma constante UPLOAD_DIRECTORY que armazena o diretório de upload como
    // o diretório atual do sistema + "/Uploads"
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/Uploads";

    @PostMapping("/upload/{id}")
    @ApiOperation(value = "UPLOAD DE ARQUIVO EM UM PRODUTO EXPECIFICO")
    public void uploadImage(@RequestParam("image") MultipartFile file, @PathVariable long id) throws IOException {

        // Define o caminho completo (Path) para o arquivo a ser salvo, com base no
        // diretório de upload e no nome de arquivo original
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        // Obtém um produto do mercado com base no ID fornecido
        ProdutoResponseDTO prdutoEncontrado = produtoServiceAction.obterPorId(id);
        // Define o nome do arquivo no produto com base no nome original do arquivo
        prdutoEncontrado.setNomeFile(file.getOriginalFilename());
        // Define o caminho do arquivo no produto com base no diretório de upload e no
        // nome do arquivo
        prdutoEncontrado.setPathFile(UPLOAD_DIRECTORY + "/" + prdutoEncontrado.getNomeFile());
        // Atualiza o produto no serviço do mercado
        // produtoServiceAction.atualizar(id, prdutoEncontrado); obs tem que verificar o
        // pq não está funcionando com o DTO?
    }
}