/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.henrique.FastFuriousAPI.domain.dto;

import br.com.henrique.FastFuriousAPI.domain.model.Categoria;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author sesi3dia
 */
public class ProdutoUpdateDTO {    
   
    private String nome;

    @Positive(message = "O preço deve ser positivo!")
    private Double preco;

    
    private Categoria categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;   
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
}
