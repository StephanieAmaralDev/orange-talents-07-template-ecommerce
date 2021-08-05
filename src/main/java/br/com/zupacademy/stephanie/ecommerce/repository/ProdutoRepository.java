package br.com.zupacademy.stephanie.ecommerce.repository;

import br.com.zupacademy.stephanie.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
