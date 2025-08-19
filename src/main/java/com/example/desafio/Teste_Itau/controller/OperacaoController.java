package com.example.desafio.Teste_Itau.controller;

import com.example.desafio.Teste_Itau.dto.OperacaoRequestDto;
import com.example.desafio.Teste_Itau.entity.Operacao;
import com.example.desafio.Teste_Itau.service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operacoes")
public class OperacaoController {

    private final OperacaoService operacaoService;

    @Autowired
    public OperacaoController(OperacaoService operacaoService) {
        this.operacaoService = operacaoService;
    }

    @PostMapping
    public ResponseEntity<Operacao> realizarOperacao(@RequestBody OperacaoRequestDto dto) {
        Operacao operacaoSalva = operacaoService.realizarOperacao(dto);
        return ResponseEntity.ok(operacaoSalva);
    }
}