package br.com.serratec.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.serratec.ecommerce.dto.categoria.CategoriaRequestDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoResponseDTO;
import br.com.serratec.ecommerce.model.Categoria;
import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.model.exceptions.ResourceNotFoundException;
import br.com.serratec.ecommerce.repository.CategoriaRepository;
import br.com.serratec.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepositoryAction;

    @Autowired
    private CategoriaRepository categoriaRepository;

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
    
        CategoriaRequestDTO categoriaRequest = produtoRequest.getCategoria();
        Categoria categoria = modelMapper.map(categoriaRequest, Categoria.class);

        categoriaRepository.save(categoria);

        Produto produto = modelMapper.map(produtoRequest, Produto.class);
        produto.setIdProd(0l);
        produto.setCategoria(categoria);
        
        produto.validarEstoque();
        produto.validarValorStatus();
        
        produto = produtoRepositoryAction.save(produto);

        return modelMapper.map(produto, ProdutoResponseDTO.class);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequest) {
        obterPorId(id);

        Produto produto = modelMapper.map(produtoRequest, Produto.class);
        produto.setIdProd(id);

        return modelMapper.map(produtoRepositoryAction.save(produto), ProdutoResponseDTO.class);
    }

    public void deletar(long id) {
        obterPorId(id);
        produtoRepositoryAction.deleteById(id);
    }

}
