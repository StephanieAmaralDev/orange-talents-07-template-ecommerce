package br.com.zupacademy.stephanie.ecommerce.validacao.ranking;

import br.com.zupacademy.stephanie.ecommerce.model.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RankingVendedor {

    public void enviar(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idVendedor", compra.getDonoProduto().getId(), "idCompra", compra.getId());

        restTemplate.postForObject("http://localhost:8080/outros-servicos/ranking", request, String.class);
    }
}
