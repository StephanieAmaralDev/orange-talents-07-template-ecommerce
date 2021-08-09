package br.com.zupacademy.stephanie.ecommerce.validacao.email;

import br.com.zupacademy.stephanie.ecommerce.model.Pergunta;
import org.springframework.stereotype.Service;

@Service
public class Email {
    private final EmailSender emailSender;

    public Email(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void novaPergunta(Pergunta pergunta) {
        String tituloEmail = "Nova pergunta - "+pergunta.getProduto().getNome();

        emailSender.send(pergunta.getInteressado().getUsername(), pergunta.getTitulo(), tituloEmail, pergunta.getDonoProduto().getUsername());
    }
}
