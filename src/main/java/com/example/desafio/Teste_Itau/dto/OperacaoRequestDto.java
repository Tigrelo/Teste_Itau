package com.example.desafio.Teste_Itau.dto;

import com.example.desafio.Teste_Itau.entity.Operacao;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class OperacaoRequestDto {
    private Long idUsuario;
    private Long idAtivo;
    private BigDecimal quantidade;
    private BigDecimal precoUnitario;
    private Operacao.TipoOperacao tipoOperacao;
    private LocalDateTime dataHora;

    // --- GETTERS ---
    public Long getIdUsuario() { return idUsuario; }
    public Long getIdAtivo() { return idAtivo; }
    public BigDecimal getQuantidade() { return quantidade; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public Operacao.TipoOperacao getTipoOperacao() { return tipoOperacao; }
    public LocalDateTime getDataHora() { return dataHora; }

    // --- SETTERS (ADICIONADOS) ---
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public void setIdAtivo(Long idAtivo) { this.idAtivo = idAtivo; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
    public void setTipoOperacao(Operacao.TipoOperacao tipoOperacao) { this.tipoOperacao = tipoOperacao; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}