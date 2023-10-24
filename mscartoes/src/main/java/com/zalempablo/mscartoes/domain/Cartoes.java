package com.zalempablo.mscartoes.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Cartoes {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        @Enumerated(EnumType.STRING)
        private BandeiraDoCartao bandeiraDoCartao;
        private BigDecimal renda;
        private BigDecimal limiteDoCartao;

    public Cartoes(String nome, BandeiraDoCartao bandeiraDoCartao, BigDecimal renda, BigDecimal limiteDoCartao) {
        this.nome = nome;
        this.bandeiraDoCartao = bandeiraDoCartao;
        this.renda = renda;
        this.limiteDoCartao = limiteDoCartao;
    }
}
