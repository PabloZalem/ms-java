package com.zalempablo.msavaliadordecredito.domain.model;

import lombok.Data;

@Data
public class SituacaoCliente {
    private DadosCliente dadosCliente;
    private List<CartaoCliente> cartaoCliente;
}
