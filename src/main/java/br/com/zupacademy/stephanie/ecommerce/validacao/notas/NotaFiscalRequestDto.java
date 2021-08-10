package br.com.zupacademy.stephanie.ecommerce.validacao.notas;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequestDto {
    @NotNull
    private Long idComprador;

    @NotNull
    private Long idCompra;

    public Long getIdComprador() {
        return idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    @Override
    public String toString() {
        return "NotaFiscalRequest{" +
                "idComprador=" + idComprador +
                ", idCompra=" + idCompra +
                '}';
    }
}
