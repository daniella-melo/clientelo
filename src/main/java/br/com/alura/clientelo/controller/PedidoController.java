package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.controller.dto.PedidoDto;
import br.com.alura.clientelo.controller.dto.ProdutoDto;
import br.com.alura.clientelo.controller.form.PedidoForm;
import br.com.alura.clientelo.controller.form.ProdutoForm;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping("/new")
    public ResponseEntity<PedidoDto> inserirNovo(@RequestBody PedidoForm form,
                                                   UriComponentsBuilder uriBuilder){
        if(!form.valido()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        Pedido novo = form.converter();
        service.cadastra(novo);

        URI uri = uriBuilder.path("/api/produtos/new/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(new PedidoDto(novo));
    }
}
