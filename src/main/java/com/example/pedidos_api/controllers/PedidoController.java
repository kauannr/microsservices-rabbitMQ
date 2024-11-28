package com.example.pedidos_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pedidos_api.entities.Pedido;
import com.example.pedidos_api.services.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Pedidos", description = "Recursos para criação de um pedido")
@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private Logger logger = LoggerFactory.getLogger(Pedido.class);

    @Autowired
    PedidoService pedidoService;

    @Operation(summary = "Apenas processa e cria um novo pedido", description = "Contém as operações para criar o pedido", responses = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class)))
    })
    @PostMapping()
    public ResponseEntity<Pedido> processarPedido(@RequestBody Pedido pedido) {

        pedido = pedidoService.processarPedido(pedido);

        logger.info("Pedido recebido: {}", pedido.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @PostMapping("/enfileirar")
    @Operation(summary = "Processa, cria e envia para a fila do rabbitMQ um novo pedido", description = "Contém as operações para criar e enviar o pedido", responses = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class)))
    })
    public ResponseEntity<Pedido> processarEEnfileirar(@RequestBody Pedido pedido) {

        pedido = pedidoService.enfileirarPedido(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

}
