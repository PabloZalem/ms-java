package com.zalempablo.msavaliadordecredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoDeCartao {
    private Long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
