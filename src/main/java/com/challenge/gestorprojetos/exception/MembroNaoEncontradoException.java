package com.challenge.gestorprojetos.exception;

public class MembroNaoEncontradoException extends RuntimeException {
    public MembroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public MembroNaoEncontradoException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
