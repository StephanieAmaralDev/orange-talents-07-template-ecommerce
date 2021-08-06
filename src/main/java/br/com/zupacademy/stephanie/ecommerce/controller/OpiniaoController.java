package br.com.zupacademy.stephanie.ecommerce.controller;

import br.com.zupacademy.stephanie.ecommerce.model.Produto;
import br.com.zupacademy.stephanie.ecommerce.model.Usuario;
import br.com.zupacademy.stephanie.ecommerce.model.dto.AdicionarOpiniaoDto;
import br.com.zupacademy.stephanie.ecommerce.repository.OpiniaoRepository;
import br.com.zupacademy.stephanie.ecommerce.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class OpiniaoController {

    private final OpiniaoRepository opiniaoRepository;
    private final ProdutoRepository produtoRepository;

    public OpiniaoController(OpiniaoRepository opiniaoRepository, ProdutoRepository produtoRepository) {
        this.opiniaoRepository = opiniaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/produtos/{id}/opinioes")
    public ResponseEntity<?> cadastrar(@PathVariable Long id, @RequestBody @Valid AdicionarOpiniaoDto adicionarOpiniaoDto, @AuthenticationPrincipal Usuario usuario) {
        Optional<Produto> produtoObj = produtoRepository.findById(id);

        if (produtoObj.isEmpty())
            return ResponseEntity.badRequest().build();

        opiniaoRepository.save(adicionarOpiniaoDto.converter(produtoObj.get(), usuario));

        return ResponseEntity.ok().build();
    }
}
