package br.com.zupacademy.stephanie.ecommerce.controller;

import br.com.zupacademy.stephanie.ecommerce.model.Categoria;
import br.com.zupacademy.stephanie.ecommerce.model.dto.CategoriaDto;
import br.com.zupacademy.stephanie.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.stephanie.ecommerce.validacao.ValidadorCategoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping({"/categorias"})
public class CategoriaController {

    private CategoriaRepository categoriaRepository;
    private ValidadorCategoria validadorCategoria;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(validadorCategoria);
    }

    public CategoriaController(CategoriaRepository categoriaRepository, ValidadorCategoria validadorCategoria) {
        this.categoriaRepository = categoriaRepository;
        this.validadorCategoria = validadorCategoria;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaDto categoriaDto) {
        this.categoriaRepository.save(categoriaDto.converter(categoriaRepository));
        return ResponseEntity.ok().build();
    }
}
