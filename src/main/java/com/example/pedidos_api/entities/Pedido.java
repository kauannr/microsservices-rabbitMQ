package com.example.pedidos_api.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.pedidos_api.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Pedido implements Serializable{

    private UUID id = UUID.randomUUID();
    private String cliente;
    private List<ItemPedido> itensDoPedido = new ArrayList<>();
    private String emailParaNotificar;

    @JsonProperty(access = Access.READ_ONLY)
    private Double valorTotal = 0.0;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime instantePedido = LocalDateTime.now();
    private StatusPedido statusPedido = StatusPedido.EM_PROCESSAMENTO;

    public Pedido(String cliente, List<ItemPedido> itensDoPedido, String emailParaNotificar) {
        this.cliente = cliente;
        this.itensDoPedido = itensDoPedido;
        this.emailParaNotificar = emailParaNotificar;
    }

    public void calcularValorTotal() {
        this.valorTotal = itensDoPedido.stream().mapToDouble(ItemPedido::getSubTotal).sum();

    }

}
