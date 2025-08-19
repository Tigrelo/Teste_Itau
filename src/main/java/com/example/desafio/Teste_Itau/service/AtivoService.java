package com.example.desafio.Teste_Itau.service;

// IMPORTS CORRIGIDOS PELA IDE
import com.example.desafio.Teste_Itau.entity.Ativo;
import com.example.desafio.Teste_Itau.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AtivoService {

    private final AtivoRepository ativoRepository;

    @Autowired
    public AtivoService(AtivoRepository ativoRepository) {
        this.ativoRepository = ativoRepository;
    }

    // O compilador agora sabe que este 'Ativo' é 'com.example.desafio.Teste_Itau.entity.Ativo'
    public Ativo criarAtivo(Ativo ativo) {
        return ativoRepository.save(ativo);
    }

    // O compilador agora sabe que esta lista é de 'com.example.desafio.Teste_Itau.entity.Ativo'
    public List<Ativo> listarTodosAtivos() {
        return ativoRepository.findAll();
    }
}