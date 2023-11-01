package com.zalempablo.msavaliadordecredito.infra.clients;

import com.zalempablo.msavaliadordecredito.domain.model.Cartao;
import com.zalempablo.msavaliadordecredito.domain.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesResourcesClient {

        @GetMapping(params = "cpf")
        ResponseEntity<List<CartaoCliente>> getCartoesByCliete(@RequestParam("cpf") String cpf);

        @GetMapping(params = "renda")
        public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda);
}
