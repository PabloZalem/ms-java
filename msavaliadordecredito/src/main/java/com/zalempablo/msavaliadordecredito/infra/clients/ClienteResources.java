package com.zalempablo.msavaliadordecredito.infra.clients;

import com.zalempablo.msavaliadordecredito.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscliente" , path = "/clientes")
public interface ClienteResources {

    @GetMapping(params = "cpf")
    ResponseEntity<DadosCliente> dadosClient(@RequestParam("cpf") String cpf);
}
