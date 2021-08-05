package br.com.zupacademy.stephanie.ecommerce.controller;

import br.com.zupacademy.stephanie.ecommerce.model.Usuario;
import br.com.zupacademy.stephanie.ecommerce.model.dto.ProdutoDto;
import br.com.zupacademy.stephanie.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.stephanie.ecommerce.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping({"/produtos"})
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;


    public ProdutoController(ProdutoRepository produtoRepository,
                             CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoDto produtoDto, @AuthenticationPrincipal Usuario usuario) {
        produtoRepository.save(produtoDto.converter(categoriaRepository, usuario));

        return ResponseEntity.ok().build();
    }

}
