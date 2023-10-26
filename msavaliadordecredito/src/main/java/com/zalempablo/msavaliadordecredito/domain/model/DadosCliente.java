package com.zalempablo.msavaliadordecredito.domain.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosCliente {
    private Long id;
    private String nome;
    private Integer idade;

}
