package com.example.desafio.Teste_Itau.dto;

import java.math.BigDecimal;
import java.util.List;

public class PosicaoConsolidadaDto {

    private String nomeUsuario;
    private BigDecimal lucroPrejuizoTotalCarteira;
    private List<PosicaoAtivoDto> posicoesPorAtivo;

    // --- GETTERS E SETTERS ---
    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }
    public BigDecimal getLucroPrejuizoTotalCarteira() { return lucroPrejuizoTotalCarteira; }
    public void setLucroPrejuizoTotalCarteira(BigDecimal lucroPrejuizoTotalCarteira) { this.lucroPrejuizoTotalCarteira = lucroPrejuizoTotalCarteira; }
    public List<PosicaoAtivoDto> getPosicoesPorAtivo() { return posicoesPorAtivo; }
    public void setPosicoesPorAtivo(List<PosicaoAtivoDto> posicoesPorAtivo) { this.posicoesPorAtivo = posicoesPorAtivo; }
}