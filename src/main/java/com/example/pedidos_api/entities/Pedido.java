package com.example.pedidos_api.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.pedidos_api.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido {
    private UUID id = UUID.randomUUID();
    private String cliente;
    private List<ItemPedido> ItemPedidos;
    private Double valorTotal;
    private String emailParaNotificar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime instantePedido;
    private StatusPedido statusPedido;
}
