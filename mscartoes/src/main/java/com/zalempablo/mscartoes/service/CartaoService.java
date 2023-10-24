package com.zalempablo.mscartoes.service;

import com.zalempablo.mscartoes.domain.Cartoes;
import com.zalempablo.mscartoes.infra.repository.CartoesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {
    @Autowired
    private final CartoesRepository cartoesRepository;

    @Transactional
    public Cartoes save(Cartoes cartoes){
        return cartoesRepository.save(cartoes);
    }

    public List<Cartoes> getCartoes(Long renda){
        var rendaBigDecimal = BigDecimal.valueOf(renda);
        return cartoesRepository.findByRendaLessThanEqual(rendaBigDecimal);
    }
}
