package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.controller.dto.CategoriaDto;
import br.com.alura.clientelo.controller.dto.ProdutoDto;
import br.com.alura.clientelo.controller.form.CategoriaForm;
import br.com.alura.clientelo.controller.form.ProdutoForm;
import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.service.ProdutoService;
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
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping("/new")
    public ResponseEntity<ProdutoDto> inserirNovo(@RequestBody ProdutoForm form,
                                                           UriComponentsBuilder uriBuilder){
        if(!form.valido()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        Produto novo = form.converter();
        service.cadastra(novo);

        URI uri = uriBuilder.path("/api/produtos/new/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(novo));
    }
}
