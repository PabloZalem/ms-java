package com.zalempablo.mscliente.resource;

import com.zalempablo.mscliente.domain.Clientes;
import com.zalempablo.mscliente.infra.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Clientes save(Clientes cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Clientes> getByCpf(String cpf){
        return clienteRepository.findByCpf(cpf);
    }
}
