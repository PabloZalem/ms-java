package com.zalempablo.mscartoes.resource;

import com.zalempablo.mscartoes.domain.Cartoes;
import com.zalempablo.mscartoes.dto.CartaoSaveRequest;
import com.zalempablo.mscartoes.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class MsCartoesResources {

    @Autowired
    private CartaoService cartaoService;

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
}
