package br.com.zupacademy.stephanie.ecommerce.model.dto;

import br.com.zupacademy.stephanie.ecommerce.model.Usuario;
import br.com.zupacademy.stephanie.ecommerce.validacao.ValorUnico;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDto {

    @NotBlank
    @Email
    @ValorUnico(field = "email", entity = Usuario.class)
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UsuarioDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario converter() {
        return new Usuario(email, senha);
    }
}
