package com.example.desafio.Teste_Itau.dto;

import java.math.BigDecimal;

public class PosicaoAtivoDto {

    private String codigoAtivo;
    private BigDecimal quantidade;
    private BigDecimal precoMedio;
    private BigDecimal totalInvestido;
    // Futuramente, podemos adicionar: precoAtual, valorAtualPosicao, lucroPrejuizo

    // --- GETTERS E SETTERS ---
    public String getCodigoAtivo() { return codigoAtivo; }
    public void setCodigoAtivo(String codigoAtivo) { this.codigoAtivo = codigoAtivo; }
    public BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }
    public BigDecimal getPrecoMedio() { return precoMedio; }
    public void setPrecoMedio(BigDecimal precoMedio) { this.precoMedio = precoMedio; }
    public BigDecimal getTotalInvestido() { return totalInvestido; }
    public void setTotalInvestido(BigDecimal totalInvestido) { this.totalInvestido = totalInvestido; }
}