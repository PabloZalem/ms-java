package com.zalempablo.mscliente.representation;


import com.zalempablo.mscliente.domain.Clientes;
import lombok.Data;

@Data
public class ClienteSaveRequest {
        private String cpf;
        private String nome;
        private Integer idade;

        public Clientes toModel(){
            return new Clientes(cpf, nome, idade);
        }
}
