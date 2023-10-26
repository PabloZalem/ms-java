package com.zalempablo.msavaliadordecredito.ex;

public class DadosClientesNotFoundException extends Exception {
    public DadosClientesNotFoundException() {
        super("Dados do clientes acessado não encontrado");
    }
}
