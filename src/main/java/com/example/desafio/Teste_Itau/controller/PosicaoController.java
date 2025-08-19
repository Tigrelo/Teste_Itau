package com.example.desafio.Teste_Itau.controller;

import com.example.desafio.Teste_Itau.dto.PosicaoConsolidadaDto;

import com.example.desafio.Teste_Itau.service.PosicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class PosicaoController {

    private final PosicaoService posicaoService;

    @Autowired
    public PosicaoController(PosicaoService posicaoService) {
        this.posicaoService = posicaoService;
    }

    @GetMapping("/{idUsuario}/posicao-consolidada")
    public ResponseEntity<PosicaoConsolidadaDto> getPosicaoConsolidada(@PathVariable Long idUsuario) {
        PosicaoConsolidadaDto posicao = posicaoService.calcularPosicao(idUsuario);
        return ResponseEntity.ok(posicao);
    }
}