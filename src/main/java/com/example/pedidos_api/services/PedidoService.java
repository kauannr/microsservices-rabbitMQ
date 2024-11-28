package com.example.pedidos_api.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.pedidos_api.entities.Pedido;
import com.example.pedidos_api.enums.StatusPedido;

@Service
public class PedidoService {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Pedido processarPedido(Pedido pedido) {

        pedido.calcularValorTotal();

        return pedido;
    }

    /**
     * Processa o pedido e envia para a fila do RabbitQM
     * 
     * @param pedido O pedido a ser processado e enviado
     * @return O pedido processado
     */
    public Pedido enfileirarPedido(Pedido pedido) {
        processarPedido(pedido);
        pedido.setStatusPedido(StatusPedido.APROVADO);

        rabbitTemplate.convertAndSend(exchangeName, "", pedido);
        return pedido;
    }
}
