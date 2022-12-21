package br.com.alura.clientelo.controller.produto;

import br.com.alura.clientelo.controller.produto.dto.ProdutoDto;
import br.com.alura.clientelo.controller.produto.dto.ProdutoListagemDto;
import br.com.alura.clientelo.controller.produto.form.ProdutoForm;
import br.com.alura.clientelo.model.produto.Produto;
import br.com.alura.clientelo.service.categoria.CategoriaService;
import br.com.alura.clientelo.service.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@Profile(value = {"prod", "test"})
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/new")
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
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

    @GetMapping("/all")
    @Cacheable(value = "listaDeProdutos")
    public ResponseEntity<List<ProdutoListagemDto>> listAll(UriComponentsBuilder uriBuilder,
            @PageableDefault(sort="nome", direction = Sort.Direction.ASC, page = 0, size = 5) Pageable paginacao){
        try {
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
