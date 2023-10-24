package com.zalempablo.mscartoes.infra.repository;

import com.zalempablo.mscartoes.domain.Cartoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CartoesRepository extends JpaRepository<Cartoes, Long> {

    List<Cartoes> findByRendaLessThanEqual(BigDecimal valor);
}
