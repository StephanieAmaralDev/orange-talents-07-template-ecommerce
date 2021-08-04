package br.com.zupacademy.stephanie.ecommerce.model.dto;

import br.com.zupacademy.stephanie.ecommerce.model.Categoria;
import br.com.zupacademy.stephanie.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.stephanie.ecommerce.validacao.ValorUnico;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @ValorUnico(field = "nome", entity = Categoria.class)
    private String nome;

    private Long categoriaMae;


    public Long getCategoriaMae() {
        return categoriaMae;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoriaDto(String nome, Long categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public boolean categoriaMaePresente() {
        return categoriaMae != null;
    }
    public Categoria converter(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(nome);

        if(categoriaMaePresente()) {
            Optional<Categoria> categoriaBanco = categoriaRepository.findById(categoriaMae);
            categoria.setCategoria(categoriaBanco.get());
        }

        return categoria;
    }
}
