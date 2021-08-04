package br.com.zupacademy.stephanie.ecommerce.validacao;

import br.com.zupacademy.stephanie.ecommerce.model.Categoria;
import br.com.zupacademy.stephanie.ecommerce.model.dto.CategoriaDto;
import br.com.zupacademy.stephanie.ecommerce.repository.CategoriaRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ValidadorCategoria implements Validator {

    private final CategoriaRepository categoriaRepository;

    public ValidadorCategoria(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CategoriaDto request = (CategoriaDto) o;

        if(request.categoriaMaePresente()) {
            Optional<Categoria> categoria = categoriaRepository.findById(request.getCategoriaMae());

            if(categoria.isEmpty())
                errors.rejectValue("categoriaMae", null,"Categoria mãe não existe");
        }
    }
}
