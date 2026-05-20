/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.henrique.FastFuriousAPI.domain.service;

import br.com.henrique.FastFuriousAPI.domain.dto.ProdutoUpdateDTO;
import br.com.henrique.FastFuriousAPI.domain.exception.ResourceNotFoundException;
import br.com.henrique.FastFuriousAPI.domain.model.Categoria;
import br.com.henrique.FastFuriousAPI.domain.model.Produto;
import br.com.henrique.FastFuriousAPI.domain.repository.ProdutoRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sesi3dia
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public List<Produto> findByCategoria(Categoria categoria) {
        return produtoRepository.findByCategoria(categoria);
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public Produto salvarProduto(Produto produtoNovo) {
        return produtoRepository.save(produtoNovo);
    }

    public Produto atualizaProduto(Integer id, ProdutoUpdateDTO dto) {
        // 1. Busca ou lança 404
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));

        // 2. Atualiza apenas o que foi enviado (os nulos são ignorados)
        if (dto.getNome() != null) {
            produtoExistente.setNome(dto.getNome());
        }

        if (dto.getPreco() != null) {
            produtoExistente.setPreco(BigDecimal.valueOf(dto.getPreco()));
        }

        if (dto.getCategoria() != null) {
            produtoExistente.setCategoria(dto.getCategoria());
        }

        // 3. Salva a entidade atualizada
        return produtoRepository.save(produtoExistente);

    }

    public void deletaProduto(Integer id) {
        produtoRepository.deleteById(id);
    }
}
