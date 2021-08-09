package br.com.zupacademy.stephanie.ecommerce.validacao.email;

import javax.validation.constraints.NotBlank;

public interface EmailSender {

    void send(@NotBlank String remetente, @NotBlank String mensagem, @NotBlank String titulo, @NotBlank String destinatario);
}
