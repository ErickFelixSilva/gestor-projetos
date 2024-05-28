package com.challenge.gestorprojetos.controller;

import com.challenge.gestorprojetos.exception.PessoaNaoEncontradaException;
import com.challenge.gestorprojetos.exception.ProjetoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProjetoNaoEncontradoException.class, PessoaNaoEncontradaException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleProjetoNaoEncontradoException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("mensagem", ex.getMessage());
        return modelAndView;
    }
}
