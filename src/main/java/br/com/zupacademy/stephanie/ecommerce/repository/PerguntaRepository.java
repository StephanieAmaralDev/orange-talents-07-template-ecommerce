package br.com.zupacademy.stephanie.ecommerce.repository;

import br.com.zupacademy.stephanie.ecommerce.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
