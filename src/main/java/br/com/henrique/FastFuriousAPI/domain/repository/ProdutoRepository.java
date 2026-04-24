/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.henrique.FastFuriousAPI.domain.repository;

import br.com.henrique.FastFuriousAPI.domain.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sesi3dia
 */
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByCategoria(String categoria);
}
