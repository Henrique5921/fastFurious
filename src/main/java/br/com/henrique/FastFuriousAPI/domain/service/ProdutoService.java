/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.henrique.FastFuriousAPI.domain.service;

import br.com.henrique.FastFuriousAPI.domain.model.Produto;
import br.com.henrique.FastFuriousAPI.domain.repository.ProdutoRepository;
import java.math.BigDecimal;
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

    public Produto salvarProduto(Produto produtoNovo) {

        // 1. Validação com BigDecimal (Fail Fast)
        if (produtoNovo.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero!");
        } else {

            // 2. Se passou do if, o preço é válido.
            return produtoRepository.save(produtoNovo);
        }
    }
}
