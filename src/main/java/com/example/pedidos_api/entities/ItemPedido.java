package com.example.pedidos_api.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ItemPedido {

    private UUID id = UUID.randomUUID();
    private Produto produto;
    private Integer quantidade;

    @JsonProperty(access = Access.READ_ONLY)
    private Double subTotal;

    public ItemPedido(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        calcularSubTotal();
    }

    private void calcularSubTotal() {
        if (produto != null && quantidade != null) {
            this.subTotal = produto.getValor() * getQuantidade();
        }
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        calcularSubTotal();
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        calcularSubTotal();
    }

}
