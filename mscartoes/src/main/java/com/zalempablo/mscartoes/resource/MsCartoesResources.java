package com.zalempablo.mscartoes.resource;

import com.zalempablo.mscartoes.domain.Cartoes;
import com.zalempablo.mscartoes.domain.ClienteCartao;
import com.zalempablo.mscartoes.dto.CartaoSaveRequest;
import com.zalempablo.mscartoes.dto.ClienteCartaoResponse;
import com.zalempablo.mscartoes.service.CartaoService;
import com.zalempablo.mscartoes.service.ClienteCartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class MsCartoesResources {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private ClienteCartaoService clienteCartaoService;

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request){
        Cartoes cartoes = request.toModel();
        cartaoService.save(cartoes);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //cartoes?renda=5000
    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartoes>> getCartoesRendaAte(@RequestParam("renda") Long renda){
        List<Cartoes> cartoes = cartaoService.getCartoes(renda);
        return ResponseEntity.ok(cartoes);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClienteCartaoResponse>> getCartoesByClientes(@RequestParam("cpf") String cpf){
        List<ClienteCartao> clienteCartaos = clienteCartaoService.listCartoesByCpf(cpf);
        List<ClienteCartaoResponse> result = clienteCartaos.stream()
                .map(ClienteCartaoResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
