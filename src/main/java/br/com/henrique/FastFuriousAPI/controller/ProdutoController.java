/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.henrique.FastFuriousAPI.controller;

import br.com.henrique.FastFuriousAPI.domain.model.Produto;
import br.com.henrique.FastFuriousAPI.domain.repository.ProdutoRepository;
import br.com.henrique.FastFuriousAPI.domain.service.ProdutoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sesi3dia
 */
@RestController
@RequestMapping("/fastfurious/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Produto> buscarPorId(@PathVariable Integer id) {
        return produtoRepository.findById(id);
    }
    
    @GetMapping("/cat/{categoria}")
    public List<Produto> listarPorCategoria(@PathVariable String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }
    
    @PostMapping
    public Produto criarProduto(@RequestBody Produto produtoNovo) {
        return produtoService.salvarProduto(produtoNovo);
    }
    
    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {
        produtoAtualizado.setId(id); // Garante que vamos atualizar o produto certo
        return produtoRepository.save(produtoAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Integer id) {
        produtoRepository.deleteById(id);
    }
}