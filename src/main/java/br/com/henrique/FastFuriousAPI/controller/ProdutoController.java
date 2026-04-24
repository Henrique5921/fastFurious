/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.henrique.FastFuriousAPI.controller;

import br.com.henrique.FastFuriousAPI.domain.model.Produto;
import br.com.henrique.FastFuriousAPI.domain.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @GetMapping
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    
    @GetMapping("/cat/{categoria}")
    public List<Produto> listarPorCategoria(@PathVariable String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }
    
    @PostMapping
    public Produto criarProduto(@RequestBody Produto produtoNovo) {
        return produtoRepository.save(produtoNovo);
    }
}