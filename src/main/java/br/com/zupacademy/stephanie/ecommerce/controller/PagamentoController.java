package br.com.zupacademy.stephanie.ecommerce.controller;

import br.com.zupacademy.stephanie.ecommerce.model.Compra;
import br.com.zupacademy.stephanie.ecommerce.model.dto.PagamentoDto;
import br.com.zupacademy.stephanie.ecommerce.repository.CompraRepository;
import br.com.zupacademy.stephanie.ecommerce.repository.PagamentoRepository;
import br.com.zupacademy.stephanie.ecommerce.validacao.email.Email;
import br.com.zupacademy.stephanie.ecommerce.validacao.notas.NotasFiscais;
import br.com.zupacademy.stephanie.ecommerce.validacao.ranking.RankingVendedor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PagamentoController {
    private final RankingVendedor rankVendedor;
    private final NotasFiscais notasFiscais;
    private final PagamentoRepository pagamentoRepository;
    private final CompraRepository compraRepository;
    private final Email email;

    public PagamentoController(RankingVendedor rankVendedor, NotasFiscais notasFiscais, PagamentoRepository pagamentoRepository, CompraRepository compraRepository, Email email) {
        this.rankVendedor = rankVendedor;
        this.notasFiscais = notasFiscais;
        this.pagamentoRepository = pagamentoRepository;
        this.compraRepository = compraRepository;
        this.email = email;
    }

    @PostMapping("/retorno-{formaPagamento}/{id}")
    @Transactional
    public ResponseEntity<?> efetivarTransacao(@PathVariable Long id, @PathVariable String formaPagamento, @RequestBody @Valid PagamentoDto pagamentoRequest, UriComponentsBuilder uriBuilder) {
        Optional<Compra> compraObj = compraRepository.findById(id);
        Compra compra = compraObj.get();

        compra.salvarTransacao(pagamentoRequest, formaPagamento);

        if(compra.compraConcluida()) {
            rankVendedor.enviar(compra);
            notasFiscais.enviar(compra);
            email.sucessoPagamento(compra);
        }
        else {
            String urlNovaTentativa = uriBuilder.path("/retorno-{formaPagamento}").buildAndExpand(formaPagamento).toString();
            email.erroPagamento(compra, urlNovaTentativa);
        }

        return ResponseEntity.ok().build();
    }
}
