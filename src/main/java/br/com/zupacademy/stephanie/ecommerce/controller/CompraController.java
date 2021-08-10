package br.com.zupacademy.stephanie.ecommerce.controller;

import br.com.zupacademy.stephanie.ecommerce.model.Compra;
import br.com.zupacademy.stephanie.ecommerce.model.GatewayPagamento;
import br.com.zupacademy.stephanie.ecommerce.model.Produto;
import br.com.zupacademy.stephanie.ecommerce.model.Usuario;
import br.com.zupacademy.stephanie.ecommerce.model.dto.DadosCompraDto;
import br.com.zupacademy.stephanie.ecommerce.model.dto.erro.ErrorFormatDTO;
import br.com.zupacademy.stephanie.ecommerce.repository.CompraRepository;
import br.com.zupacademy.stephanie.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.stephanie.ecommerce.validacao.email.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final ProdutoRepository produtoRepository;
    private final CompraRepository compraRepository;
    private final Email email;

    public CompraController(ProdutoRepository produtoRepository, CompraRepository compraRepository, Email email) {
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
        this.email = email;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> comprar(@RequestBody @Valid DadosCompraDto dadosCompraDto, @AuthenticationPrincipal Usuario usuario, UriComponentsBuilder uriBuilder){
        Produto produto = produtoRepository.findById(dadosCompraDto.getProduto()).get();
        int quantidade = dadosCompraDto.getQuantidade();
        boolean verificaEstoque = produto.abaterEstoque(dadosCompraDto.getQuantidade());

        if(verificaEstoque) {
            GatewayPagamento pagamento = dadosCompraDto.getPagamento();

            Compra compra = new Compra(produto, quantidade, usuario, pagamento);
            compraRepository.save(compra);

            email.novaCompra(compra);

            return ResponseEntity.ok(pagamento.redirectUrl(compra.getId(), uriBuilder));
        }

        return ResponseEntity.badRequest().body(new ErrorFormatDTO("quantidade", "Estoque insuficiente."));
    }
}
