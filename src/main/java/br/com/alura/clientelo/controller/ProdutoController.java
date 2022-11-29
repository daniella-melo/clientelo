package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.controller.dto.ProdutoDto;
import br.com.alura.clientelo.controller.dto.ProdutoListagemDto;
import br.com.alura.clientelo.controller.form.ProdutoForm;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.service.CategoriaService;
import br.com.alura.clientelo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    //TODO: adicionar regras de ordenação e paginação
    @GetMapping("/all")
    public ResponseEntity<List<ProdutoListagemDto>> listAll(UriComponentsBuilder uriBuilder,
                                                            @RequestParam(defaultValue = "0") int pagina,
                                                            @RequestParam int qtd){
        try {
            Pageable paginacao = PageRequest.of(pagina, qtd);

            Page<Produto> all = service.listaTodos(paginacao);
            List<ProdutoListagemDto> dto = new ArrayList<>();
            all.forEach(p -> {
                ProdutoListagemDto obj = new ProdutoListagemDto(p.getNome(), p.getPrecoUnitario(),
                        p.getDescricao(), p.getQntEmEstoque(), p.getCategoria().getId(),
                        p.getCategoria().getNome());
                dto.add(obj);
            });
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
