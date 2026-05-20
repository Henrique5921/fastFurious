/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.henrique.FastFuriousAPI.controller;

import br.com.henrique.FastFuriousAPI.domain.dto.ProdutoUpdateDTO;
import br.com.henrique.FastFuriousAPI.domain.model.Categoria;
import br.com.henrique.FastFuriousAPI.domain.model.Produto;
import br.com.henrique.FastFuriousAPI.domain.repository.ProdutoRepository;
import br.com.henrique.FastFuriousAPI.domain.service.ProdutoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Produto> buscarPorId(@PathVariable Integer id) {
        return produtoService.buscarPorId(id);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Produto>> listarPorCategoria(@PathVariable String categoria) {
        // 1. Converte a String recebida na URL para o Enum (forçando letras maiúsculas para evitar erros)
        Categoria categoriaEnum = Categoria.valueOf(categoria.toUpperCase());

        // 2. Passa o Enum para o Service/Repository
        List<Produto> produtos = produtoService.findByCategoria(categoriaEnum);

        // 3. Retorna a lista
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public Produto criarProduto(@Valid @RequestBody Produto produtoNovo) {
        return produtoService.salvarProduto(produtoNovo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @Valid @RequestBody ProdutoUpdateDTO dto) {
        Produto produto = produtoService.atualizaProduto(id, dto);

        return ResponseEntity.ok().body(produto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Integer id) {
        produtoService.deletaProduto(id);
    }
}
