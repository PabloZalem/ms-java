package com.zalempablo.msavaliadordecredito.ex;

import lombok.Getter;

public class ErroComunicacaoException extends Exception {
    @Getter
    private Integer status;

    public ErroComunicacaoException(String message) {
        super(message);
        this.status = status;
    }
}
