/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.henrique.FastFuriousAPI.domain.service;

import br.com.henrique.FastFuriousAPI.domain.dto.PedidoRequestDTO;
import br.com.henrique.FastFuriousAPI.domain.dto.PedidoStatusDTO;
import br.com.henrique.FastFuriousAPI.domain.exception.ResourceNotFoundException;
import br.com.henrique.FastFuriousAPI.domain.model.Pedido;
import br.com.henrique.FastFuriousAPI.domain.model.Produto;
import br.com.henrique.FastFuriousAPI.domain.model.StatusPedido;
import br.com.henrique.FastFuriousAPI.domain.repository.PedidoRepository;
import br.com.henrique.FastFuriousAPI.domain.repository.ProdutoRepository;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sesi3dia
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Pedido insert(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();

        pedido.setMomento(Instant.now());

        pedido.setStatus(StatusPedido.ABERTO);

        for (Integer id : dto.produtosIds()) {
            Produto produto = produtoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado! ID: " + id));

            pedido.getItens().add(produto);
        }
        return repository.save(pedido);
    }

    @Transactional(readOnly = true)
    public List<Pedido> findByStatus(StatusPedido status) {
        return repository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<Pedido> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Pedido findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado! ID: " + id));
    }

    @Transactional

    public Pedido atualizarProdutos(Long id, PedidoRequestDTO dto) {
        Pedido pedidoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (dto.produtosIds() != null && !dto.produtosIds().isEmpty()) {
            List<Produto> novosProdutos = produtoRepository.findAllById(dto.produtosIds());
            pedidoExistente.setItens(novosProdutos);
        }

        return repository.save(pedidoExistente);
    }

// Método 2: Exclusivo para mudar o status
    public Pedido atualizarStatus(Long id, PedidoStatusDTO dto) {
        Pedido pedidoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (dto.status() != null) {
            pedidoExistente.setStatus(dto.status());
            // Adapte a linha acima conforme o seu Enum
        }

        return repository.save(pedidoExistente);
    }

    @Transactional
    public void delete(Long id) {
        Pedido entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado! ID: " + id));

        entity.setStatus(StatusPedido.CANCELADO);
        repository.save(entity);
    }

}
