package com.example.desafio.Teste_Itau.service;

import com.example.desafio.Teste_Itau.dto.OperacaoRequestDto;
import com.example.desafio.Teste_Itau.entity.Ativo;
import com.example.desafio.Teste_Itau.entity.Operacao;
import com.example.desafio.Teste_Itau.entity.Usuario;
import com.example.desafio.Teste_Itau.repository.AtivoRepository;
import com.example.desafio.Teste_Itau.repository.OperacaoRepository;
import com.example.desafio.Teste_Itau.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperacaoService {

    private final OperacaoRepository operacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AtivoRepository ativoRepository;

    @Autowired
    public OperacaoService(OperacaoRepository operacaoRepository, UsuarioRepository usuarioRepository, AtivoRepository ativoRepository) {
        this.operacaoRepository = operacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ativoRepository = ativoRepository;
    }

    @Transactional // Garante que todas as operações com o banco sejam executadas com sucesso, ou nenhuma é.
    public Operacao realizarOperacao(OperacaoRequestDto dto) {
        // 1. Busca as entidades referenciadas (Usuario e Ativo)
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Ativo ativo = ativoRepository.findById(dto.getIdAtivo())
                .orElseThrow(() -> new RuntimeException("Ativo não encontrado"));

        // 2. Cria a nova entidade Operacao
        Operacao novaOperacao = new Operacao();
        novaOperacao.setUsuario(usuario);
        novaOperacao.setAtivo(ativo);
        novaOperacao.setQuantidade(dto.getQuantidade());
        novaOperacao.setPrecoUnitario(dto.getPrecoUnitario());
        novaOperacao.setTipoOperacao(dto.getTipoOperacao());
        novaOperacao.setDataHora(dto.getDataHora());

        // 3. Salva a nova operação no banco
        return operacaoRepository.save(novaOperacao);
    }
}