package br.com.zupacademy.stephanie.ecommerce.model.dto;

import br.com.zupacademy.stephanie.ecommerce.model.Caracteristica;
import br.com.zupacademy.stephanie.ecommerce.model.ListaOpinioes;
import br.com.zupacademy.stephanie.ecommerce.model.Pergunta;
import br.com.zupacademy.stephanie.ecommerce.model.Produto;

import java.util.Map;
import java.util.Set;

public class DetalhesProdutoDto {
    private Set<String> linksImagens;
    private String nome;
    private Double preco;
    private Set<Caracteristica> caracteristicas;
    private String descricao;
    private Double mediaNotas;
    private Integer qtdNotas;
    private Set<Map<String, String>> opinoes;
    private Set<String> perguntas;

    public DetalhesProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = produto.getCaracteristicas();
        this.descricao = produto.getDescricao();
        this.linksImagens = produto.getImagens();

        this.perguntas = produto.mapeiaPerguntas(Pergunta::getTitulo);

        ListaOpinioes listaOpinioes = new ListaOpinioes(produto.getOpinioes());

        this.opinoes = listaOpinioes.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
        });

        this.qtdNotas = listaOpinioes.total();
        this.mediaNotas = listaOpinioes.media();
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getQtdNotas() {
        return qtdNotas;
    }

    public Set<Map<String, String>> getOpinoes() {
        return opinoes;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }
}
