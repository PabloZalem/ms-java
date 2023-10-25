package com.zalempablo.mscartoes.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class ClienteCartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    @ManyToMany
    @JoinTable(name = "cliente_cartao_cartoes",
            joinColumns = @JoinColumn(name = "cliente_cartao_id"),
            inverseJoinColumns = @JoinColumn(name = "cartao_id"))
    private List<Cartoes> cartoes;
    private BigDecimal limite;

}
