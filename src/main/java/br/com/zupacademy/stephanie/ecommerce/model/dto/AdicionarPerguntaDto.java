package br.com.zupacademy.stephanie.ecommerce.model.dto;

import br.com.zupacademy.stephanie.ecommerce.model.Pergunta;
import br.com.zupacademy.stephanie.ecommerce.model.Produto;
import br.com.zupacademy.stephanie.ecommerce.model.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class AdicionarPerguntaDto {
    @NotBlank
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AdicionarPerguntaDto(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta converter(Usuario interessado, Produto produto) {
        return new Pergunta(titulo, interessado, produto);
    }
}
