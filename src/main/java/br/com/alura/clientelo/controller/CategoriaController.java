package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.controller.dto.CategoriaDto;
import br.com.alura.clientelo.controller.form.CategoriaForm;
import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping("/new")
    public ResponseEntity<CategoriaDto> inserirNova(@Valid CategoriaForm form,
                                                    UriComponentsBuilder uriBuilder,
                                                    BindingResult result){
        if(result.hasFieldErrors()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        Categoria novaCategoria = form.converter();
        service.cadastra(novaCategoria);

        URI uri = uriBuilder.path("/api/categorias/new/{id}").buildAndExpand(novaCategoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(novaCategoria));
    }
}
