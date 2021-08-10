package br.com.zupacademy.stephanie.ecommerce.model.dto;

import br.com.zupacademy.stephanie.ecommerce.model.Compra;
import br.com.zupacademy.stephanie.ecommerce.model.GatewayPagamento;
import br.com.zupacademy.stephanie.ecommerce.model.Pagamento;
import br.com.zupacademy.stephanie.ecommerce.model.StatusPagamento;

import javax.validation.constraints.NotNull;

public class PagamentoDto {
    @NotNull
    private String idTransacao;

    @NotNull
    private StatusPagamento status;

    public String getIdTransacao() {
        return idTransacao;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public Pagamento converter(Compra compra, GatewayPagamento formaPagamento) {
        return new Pagamento(compra, idTransacao, status, formaPagamento);
    }

}
