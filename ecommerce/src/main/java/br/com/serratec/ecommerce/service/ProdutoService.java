package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.serratec.ecommerce.dto.produto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.enums.TipoEntidade;
import br.com.serratec.ecommerce.model.Categoria;
import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.model.exceptions.ResourceBadRequestException;
import br.com.serratec.ecommerce.model.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepositoryAction;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProdutoResponseDTO> obterTodos() {

        return produtoRepositoryAction.findAll()
                .stream()
                .map(produto -> modelMapper.map(produto, ProdutoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO obterPorId(Long id) {
        Optional<Produto> optProduto = produtoRepositoryAction.findById(id);

        if (optProduto.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum registro encontardo para o id: " + id);
        }
        return modelMapper.map(optProduto.get(), ProdutoResponseDTO.class);
    }

    @Transactional
    public ProdutoResponseDTO adcionar(ProdutoRequestDTO produtoRequest) {

        Produto produtoModel = modelMapper.map(produtoRequest, Produto.class);

        Categoria categoria = modelMapper.map(categoriaService.obterPorId(produtoRequest.getIdCategoria()),
                Categoria.class);

        verificarCategoria(categoria);

        produtoModel.setCategoria(categoria);

        produtoModel.validarEstoque();
        produtoModel.validarValorStatus();

        produtoModel = produtoRepositoryAction.save(produtoModel);

        auditoriaService.infoRegistarAuditoria(TipoEntidade.PRODUTO, "Cadastro", "", produtoModel);

        return modelMapper.map(produtoModel, ProdutoResponseDTO.class);

    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequest) {
        
        var prodBanco = obterPorId(id);

        Categoria categoria = modelMapper.map(categoriaService.obterPorId(produtoRequest.getIdCategoria()), Categoria.class);

        verificarCategoria(categoria);

        if(produtoRequest.getEstoqueProd() == 0) {
            produtoRequest.setStatusProd(false);
        }

        Produto produtoModel = modelMapper.map(produtoRequest, Produto.class);

        produtoModel.setIdProd(id);

        auditoriaService.infoRegistarAuditoria(TipoEntidade.PRODUTO, "Atualizado", prodBanco, produtoModel);

        return modelMapper.map(produtoRepositoryAction.save(produtoModel), ProdutoResponseDTO.class);
    }

    public void deletar(long id) {
        Optional<Produto> produto = produtoRepositoryAction.findById(id);

        if(produto.isPresent()) {
            produto.get().setStatusProd(false);
         produtoRepositoryAction.save(produto.get());
        }
    } 

    private void verificarCategoria(Categoria categoria) {
        if(categoria.getStatusCate() == false) {
            throw new ResourceBadRequestException("Não é possível adicionar um produto com a categoria: " + categoria.getNmCategoria() + " porque ela está desativada");
        }
    }    
}
