package br.com.zupacademy.stephanie.ecommerce.repository;

import br.com.zupacademy.stephanie.ecommerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
