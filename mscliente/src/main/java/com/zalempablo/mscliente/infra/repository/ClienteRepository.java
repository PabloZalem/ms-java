package com.zalempablo.mscliente.infra.repository;

import com.zalempablo.mscliente.domain.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Clientes,Long> {
    Optional<Clientes> findByCpf(String cpf);
}
