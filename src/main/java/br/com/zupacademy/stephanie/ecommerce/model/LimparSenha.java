package br.com.zupacademy.stephanie.ecommerce.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LimparSenha {

    @NotBlank
    @Size(min = 6)
    private String senha;

    public LimparSenha(@NotBlank @Size(min = 6) String senha) {
        Assert.hasLength(senha, "Insira uma senha válida.");
        Assert.isTrue(senha.length() >= 6, "Insira ao menos 6 caracteres");

        this.senha = senha;
    }
    public String getSenha() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}