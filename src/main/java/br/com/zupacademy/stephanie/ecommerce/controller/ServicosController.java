package br.com.zupacademy.stephanie.ecommerce.controller;

import br.com.zupacademy.stephanie.ecommerce.validacao.notas.NotaFiscalRequestDto;
import br.com.zupacademy.stephanie.ecommerce.validacao.ranking.RankingVendedorRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/outros-servicos")
public class ServicosController {

    @PostMapping("/notas-fiscais")
    public ResponseEntity<?> criarNota(@RequestBody @Valid NotaFiscalRequestDto notaRequest) {
        System.out.println("Nota fiscal criada: "+notaRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ranking")
    public ResponseEntity<?> ranking(@RequestBody @Valid RankingVendedorRequestDto rankingRequest) {
        System.out.println("Ranking de vendedores atualizado: "+rankingRequest);

        return ResponseEntity.ok().build();
    }
}
