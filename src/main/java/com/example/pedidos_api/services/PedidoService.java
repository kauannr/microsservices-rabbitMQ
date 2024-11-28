package com.example.pedidos_api.services;

import org.springframework.stereotype.Service;

import com.example.pedidos_api.entities.Pedido;

@Service
public class PedidoService {

    public Pedido processarPedido(Pedido pedido) {

        pedido.calcularValorTotal();

        return pedido;
    }
}
