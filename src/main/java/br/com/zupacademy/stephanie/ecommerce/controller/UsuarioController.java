package br.com.zupacademy.stephanie.ecommerce.controller;

import br.com.zupacademy.stephanie.ecommerce.model.dto.UsuarioDto;
import br.com.zupacademy.stephanie.ecommerce.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioDto usuarioDto) {
        usuarioRepository.save(usuarioDto.converter());

        return ResponseEntity.ok().build();
    }
}
