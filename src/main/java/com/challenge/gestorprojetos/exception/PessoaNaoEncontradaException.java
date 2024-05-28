package com.challenge.gestorprojetos.exception;

public class PessoaNaoEncontradaException extends RuntimeException {
    public PessoaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
    public PessoaNaoEncontradaException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
