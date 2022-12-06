package br.com.alura.clientelo.controller;

import br.com.alura.clientelo.controller.dto.*;
import br.com.alura.clientelo.controller.form.PedidoForm;
import br.com.alura.clientelo.controller.form.ProdutoForm;
import br.com.alura.clientelo.model.Pedido;
import br.com.alura.clientelo.model.Produto;
import br.com.alura.clientelo.projecao.VendaPorCategoriaProjecao;
import br.com.alura.clientelo.service.ClienteService;
import br.com.alura.clientelo.service.PedidoService;
import br.com.alura.clientelo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoService pedidoService;
    @PostMapping("/new")
    @CacheEvict(value = "listaDePedidos", allEntries = true)
    public ResponseEntity<PedidoDto> inserirNovo(@Valid PedidoForm form,
                                                 UriComponentsBuilder uriBuilder,
                                                 BindingResult result){

        if(result.hasErrors() || !form.valido(clienteService,produtoService)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        Pedido novo = form.converter(clienteService, produtoService, pedidoService);
        service.cadastra(novo);

        URI uri = uriBuilder.path("/api/produtos/new/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(new PedidoDto(novo));
    }

    @GetMapping("/all")
    @Cacheable(value = "listaDePedidos")
    public ResponseEntity<List<PedidoListagemDto>> listAll(UriComponentsBuilder uriBuilder,
         @PageableDefault(sort="data", direction = Sort.Direction.DESC, page = 0, size = 5) Pageable paginacao){
        try {
            Page<Pedido> all = service.listaTodos(paginacao);
            List<PedidoListagemDto> dto = new ArrayList<>();
            all.forEach(p -> {
                PedidoListagemDto obj = new PedidoListagemDto(p.getData(), p.getValorTotal(),
                        p.getDescontoTotal(), p.getQuantidadeProdutosVendidos(), p.getCliente());
                dto.add(obj);
            });
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value={"/{id}"}, method=RequestMethod.GET)
    public ResponseEntity<PedidoDetailsDto> getById(@PathVariable(value="id") final Long id,
                                                    UriComponentsBuilder uriBuilder){
        try {
            Optional<Pedido> recoveredPedido = pedidoService.getById(id);
            if(!recoveredPedido.isPresent()){
                return ResponseEntity.notFound().build();
            }
            PedidoDetailsDto dto = new PedidoDetailsDto(recoveredPedido.get());
            URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(recoveredPedido.get().getId()).toUri();
            return ResponseEntity.created(uri).body(dto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @GetMapping("/vendas")
//    @Cacheable(value = "listaDePedidos")
//    public ResponseEntity<List<VendaPorCategoriaDto>> vendas(){
//        try {
//            List<VendaPorCategoriaProjecao> vendas = pedidoService.getVendasPorCategoria();
//
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
}
