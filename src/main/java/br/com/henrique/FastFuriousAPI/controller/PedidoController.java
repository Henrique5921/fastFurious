/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.henrique.FastFuriousAPI.controller;

import br.com.henrique.FastFuriousAPI.domain.dto.PedidoRequestDTO;
import br.com.henrique.FastFuriousAPI.domain.dto.PedidoStatusDTO;
import br.com.henrique.FastFuriousAPI.domain.model.Pedido;
import br.com.henrique.FastFuriousAPI.domain.model.StatusPedido;
import br.com.henrique.FastFuriousAPI.domain.service.PedidoService;
import java.net.URI;
import java.util.List;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author sesi3dia
 */
@RestController
@RequestMapping("/fastfurious/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<Pedido> inserir(@RequestBody PedidoRequestDTO dto) {
        Pedido pedido = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedido.getId())
                .toUri();

        return ResponseEntity.created(uri).body(pedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> buscarTodos() {
        List<Pedido> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok().body(pedido);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pedido>> buscarPorStatus(@PathVariable StatusPedido status) {
        List<Pedido> lista = service.findByStatus(status);
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody PedidoRequestDTO dto) {
        Pedido pedido = service.atualizarProdutos(id, dto);
        return ResponseEntity.ok().body(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id, @RequestBody PedidoStatusDTO dto) {
        Pedido pedido = service.atualizarStatus(id, dto);
        return ResponseEntity.ok().body(pedido);
    }
}
