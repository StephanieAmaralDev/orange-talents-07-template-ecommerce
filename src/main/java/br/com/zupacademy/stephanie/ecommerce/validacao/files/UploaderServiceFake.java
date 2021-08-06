package br.com.zupacademy.stephanie.ecommerce.validacao.files;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Profile("dev")
public class UploaderServiceFake implements Uploader {

    @Override
    public Set<String> enviar(List<MultipartFile> imagens) {
        Assert.notEmpty(imagens, "As imagens não podem estar vazias");
        return imagens.stream().map(img -> "https://uploadfile.com/file/"+img.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
