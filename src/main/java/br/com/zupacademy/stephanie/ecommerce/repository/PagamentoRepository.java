package br.com.zupacademy.stephanie.ecommerce.repository;

import br.com.zupacademy.stephanie.ecommerce.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
