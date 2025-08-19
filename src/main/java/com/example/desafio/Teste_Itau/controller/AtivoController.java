package com.example.desafio.Teste_Itau.controller;


import com.example.desafio.Teste_Itau.entity.Ativo;
import com.example.desafio.Teste_Itau.service.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marca a classe como um Controller da API REST
@RequestMapping("/api/ativos") // Todas as requisições que começarem com /api/ativos virão para cá
public class AtivoController {

    @Autowired // O Spring injeta o serviço que criamos
    private AtivoService ativoService;

    @PostMapping // Mapeia requisições HTTP POST para este método
    public ResponseEntity<Ativo> criarAtivo(@RequestBody Ativo ativo) {
        Ativo novoAtivo = ativoService.criarAtivo(ativo);
        return ResponseEntity.ok(novoAtivo);
    }

    @GetMapping // Mapeia requisições HTTP GET para este método
    public ResponseEntity<List<Ativo>> listarAtivos() {
        List<Ativo> ativos = ativoService.listarTodosAtivos();
        return ResponseEntity.ok(ativos);
    }
}