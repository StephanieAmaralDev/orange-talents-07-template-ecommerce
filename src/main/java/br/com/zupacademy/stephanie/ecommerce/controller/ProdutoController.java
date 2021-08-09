package br.com.zupacademy.stephanie.ecommerce.controller;

import br.com.zupacademy.stephanie.ecommerce.model.Produto;
import br.com.zupacademy.stephanie.ecommerce.model.Usuario;
import br.com.zupacademy.stephanie.ecommerce.model.dto.AdicionarImagemDto;
import br.com.zupacademy.stephanie.ecommerce.model.dto.ProdutoDto;
import br.com.zupacademy.stephanie.ecommerce.repository.CategoriaRepository;
import br.com.zupacademy.stephanie.ecommerce.repository.ProdutoRepository;
import br.com.zupacademy.stephanie.ecommerce.validacao.files.Uploader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping({"/produtos"})
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final Uploader uploaderFile;
    private final CategoriaRepository categoriaRepository;

    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, Uploader uploaderFile) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.uploaderFile = uploaderFile;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoDto produtoDto, @AuthenticationPrincipal Usuario usuario) {
        produtoRepository.save(produtoDto.converter(categoriaRepository, usuario));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionarImagens(@PathVariable Long id, @Valid AdicionarImagemDto imagens, @AuthenticationPrincipal Usuario usuario) {
        Optional<Produto> produtoObj = produtoRepository.findById(id);

        //Verifica se o produto está cadastrado
        if(produtoObj.isEmpty())
            return ResponseEntity.badRequest().build();

        //Verifica se o usuário é o dono do produto
        if(!produtoObj.get().isDono(usuario))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        //Faz o upload das imagens
        Set<String> listaLinks = uploaderFile.enviar(imagens.getImagens());

        //Salva as imagens no produto
        produtoObj.get().adicionaImagens(listaLinks);

        return ResponseEntity.ok().build();
    }

}
