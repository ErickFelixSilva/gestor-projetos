package com.challenge.gestorprojetos.exception;

public class ProjetoNaoEncontradoException extends RuntimeException {
    public ProjetoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public ProjetoNaoEncontradoException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
