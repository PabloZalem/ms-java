package com.zalempablo.msavaliadordecredito.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosCliente {
    private Long id;
    private String nome;
    private Integer idade;

}
