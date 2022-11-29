package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.controller.dto.CategoriaDto;
import br.com.alura.clientelo.controller.dto.ProdutoDto;
import br.com.alura.clientelo.controller.dto.ProdutoListagemDto;
import br.com.alura.clientelo.controller.form.CategoriaForm;
import br.com.alura.clientelo.controller.form.ProdutoForm;
import br.com.alura.clientelo.model.Categoria;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.service.CategoriaService;
import br.com.alura.clientelo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/new")
    public ResponseEntity<ProdutoDto> inserirNovo(@RequestBody @Valid ProdutoForm form,
                                                  UriComponentsBuilder uriBuilder,
                                                  BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        Produto novo = form.converter(categoriaService);
        service.cadastra(novo);

        URI uri = uriBuilder.path("/api/produtos/new/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(novo));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProdutoListagemDto>> listAll(UriComponentsBuilder uriBuilder){
        try {
            List<Produto> all = service.listaTodos();
            List<ProdutoListagemDto> dto = new ArrayList<>();
            all.forEach(p -> {
                ProdutoListagemDto obj = new ProdutoListagemDto(p.getNome(), p.getPrecoUnitario(),
                        p.getDescricao(), p.getQntEmEstoque(), p.getCategoria().getId(),
                        p.getCategoria().getNome());
                dto.add(obj);
            });
            URI uri = uriBuilder.path("/api/produtos/all").buildAndExpand(dto).toUri();
            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
