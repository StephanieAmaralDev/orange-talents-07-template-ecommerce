package br.com.zupacademy.stephanie.ecommerce.controller;

import br.com.zupacademy.stephanie.ecommerce.model.dto.LoginDto;
import br.com.zupacademy.stephanie.ecommerce.model.dto.TokenDTO;
import br.com.zupacademy.stephanie.ecommerce.validacao.TokenManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AutenticationController {

    private final TokenManager tokenManager;
    private final AuthenticationManager authenticationManager;

    public AutenticationController(TokenManager tokenManager, AuthenticationManager authenticationManager) {
        this.tokenManager = tokenManager;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginDto login) {
        UsernamePasswordAuthenticationToken dadosLogin = login.converter();

        try{
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenManager.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
