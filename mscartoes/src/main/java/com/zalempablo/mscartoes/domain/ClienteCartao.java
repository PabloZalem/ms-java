package com.zalempablo.mscartoes.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class ClienteCartao {
    @Id
    @GeneratedValue(strategy = )
    private Long id;
    private String cpf;
    private Cartoes cartoes;
    private BigDecimal limite;

}
