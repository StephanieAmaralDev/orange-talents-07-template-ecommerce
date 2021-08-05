package br.com.zupacademy.stephanie.ecommerce.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public LoginDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
