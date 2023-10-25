package com.zalempablo.mscartoes.dto;

import com.zalempablo.mscartoes.domain.Cartoes;
import com.zalempablo.mscartoes.domain.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCartaoResponse {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static ClienteCartaoResponse fromModel(ClienteCartao clienteCartao) {
        // Certifique-se de que a classe ClienteCartao tenha a lista de Cartao
        // e atualize o código a seguir de acordo com a estrutura real da sua classe.
        List<Cartoes> cartoes = clienteCartao.getCartoes();
        String nomeCartao = cartoes != null && !cartoes.isEmpty() ? cartoes.get(0).getNome() : "";
        String bandeiraDoCartao = cartoes != null && !cartoes.isEmpty() ? cartoes.get(0).getBandeiraDoCartao().toString() : "";

        return new ClienteCartaoResponse(
                nomeCartao,
                bandeiraDoCartao,
                clienteCartao.getLimite()
        );
    }
}
