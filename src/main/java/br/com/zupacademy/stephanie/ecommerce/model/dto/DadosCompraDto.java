package br.com.zupacademy.stephanie.ecommerce.model.dto;

import br.com.zupacademy.stephanie.ecommerce.model.GatewayPagamento;
import br.com.zupacademy.stephanie.ecommerce.model.Produto;
import br.com.zupacademy.stephanie.ecommerce.validacao.ValorUnico;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DadosCompraDto {

    @NotNull
    @Positive
    @ValorUnico(field = "id", entity = Produto.class)
    private Long produto;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    private GatewayPagamento pagamento;

    public Long getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getPagamento() {
        return pagamento;
    }
}
