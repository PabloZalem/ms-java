package com.zalempablo.mscartoes.dto;

import com.zalempablo.mscartoes.domain.BandeiraDoCartao;
import com.zalempablo.mscartoes.domain.Cartoes;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {
    private String nome;
    private BandeiraDoCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartoes toModel(){
        return new Cartoes(nome, bandeira, renda, limite);
    }

}
