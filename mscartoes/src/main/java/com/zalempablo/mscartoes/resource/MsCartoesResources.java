package com.zalempablo.mscartoes.resource;

import com.zalempablo.mscartoes.domain.Cartoes;
import com.zalempablo.mscartoes.dto.CartaoSaveRequest;
import com.zalempablo.mscartoes.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class MsCartoesResources {

    @Autowired
    private CartaoService cartaoService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request){
        Cartoes cartoes = request.toModel();
        cartaoService.save(cartoes);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
