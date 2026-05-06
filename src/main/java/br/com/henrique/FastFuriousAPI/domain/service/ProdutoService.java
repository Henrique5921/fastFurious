/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.henrique.FastFuriousAPI.domain.service;

import br.com.henrique.FastFuriousAPI.domain.model.Produto;
import br.com.henrique.FastFuriousAPI.domain.repository.ProdutoRepository;
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

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public Produto salvarProduto(Produto produtoNovo) {
        return produtoRepository.save(produtoNovo);
    }

    public Produto atualizaProduto(Produto produtoAtualizado, Integer id) {
        produtoAtualizado.setId(id);
        return produtoRepository.save(produtoAtualizado);
    }

    public void deletaProduto(Integer id) {
        produtoRepository.deleteById(id);
    }
}
