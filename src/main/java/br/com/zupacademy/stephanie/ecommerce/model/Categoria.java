package br.com.zupacademy.stephanie.ecommerce.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String nome;

    @ManyToOne
    private Categoria categoria;

    public Categoria(String nome) {
        Assert.hasLength(nome, "O nome não pode estar vazio");
        this.nome = nome;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria() { }




}
