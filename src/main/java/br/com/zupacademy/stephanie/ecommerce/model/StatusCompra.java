package br.com.zupacademy.stephanie.ecommerce.model;

public enum StatusCompra {

    INICIADA,
    SUCESSO,
    FALHA;

    public StatusCompra normalizar(StatusPagamento statusPagamento){
        return (statusPagamento.equals(StatusPagamento.SUCESSO)) ? StatusCompra.SUCESSO : StatusCompra.FALHA;
    }
}
