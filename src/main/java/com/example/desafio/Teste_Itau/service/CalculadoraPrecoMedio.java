package com.example.desafio.Teste_Itau.service;

import com.example.desafio.Teste_Itau.dto.PosicaoAtivoDto;
import com.example.desafio.Teste_Itau.dto.PosicaoConsolidadaDto;
import com.example.desafio.Teste_Itau.entity.Ativo;
import com.example.desafio.Teste_Itau.entity.Operacao;
import com.example.desafio.Teste_Itau.entity.Usuario;
import com.example.desafio.Teste_Itau.repository.OperacaoRepository;
import com.example.desafio.Teste_Itau.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CalculadoraPrecoMedio {

    // Classe interna para representar uma compra
    public static class Compra {
        private final BigDecimal quantidade;
        private final BigDecimal preco;

        // --- CONSTRUTOR E GETTERS ADICIONADOS ---

        public Compra(BigDecimal quantidade, BigDecimal preco) {
            this.quantidade = quantidade;
            this.preco = preco;
        }

        public BigDecimal getQuantidade() {
            return quantidade;
        }

        public BigDecimal getPreco() {
            return preco;
        }
    }

    public static BigDecimal calcular(List<Compra> compras) {
        if (compras == null || compras.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal custoTotal = BigDecimal.ZERO;
        BigDecimal quantidadeTotal = BigDecimal.ZERO;

        for (Compra compra : compras) {

            custoTotal = custoTotal.add(compra.getQuantidade().multiply(compra.getPreco()));
            quantidadeTotal = quantidadeTotal.add(compra.getQuantidade());
        }

        if (quantidadeTotal.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return custoTotal.divide(quantidadeTotal, 8, RoundingMode.HALF_UP);
    }

    @Service
    public static class PosicaoService {

        private final UsuarioRepository usuarioRepository;
        private final OperacaoRepository operacaoRepository;

        @Autowired
        public PosicaoService(UsuarioRepository usuarioRepository, OperacaoRepository operacaoRepository) {
            this.usuarioRepository = usuarioRepository;
            this.operacaoRepository = operacaoRepository;
        }

        public PosicaoConsolidadaDto calcularPosicao(Long idUsuario) {
            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            List<Operacao> operacoes = operacaoRepository.findByUsuarioId(idUsuario);

            Map<Ativo, List<Operacao>> operacoesPorAtivo = operacoes.stream()
                    .collect(Collectors.groupingBy(Operacao::getAtivo));

            List<PosicaoAtivoDto> posicoes = new ArrayList<>();

            for (Map.Entry<Ativo, List<Operacao>> entry : operacoesPorAtivo.entrySet()) {
                Ativo ativo = entry.getKey();
                List<Operacao> operacoesDoAtivo = entry.getValue();

                BigDecimal quantidadeTotal = BigDecimal.ZERO;
                List<Compra> compras = new ArrayList<>();

                for (Operacao op : operacoesDoAtivo) {
                    if (op.getTipoOperacao() == Operacao.TipoOperacao.COMPRA) {
                        quantidadeTotal = quantidadeTotal.add(op.getQuantidade());
                        compras.add(new Compra(op.getQuantidade(), op.getPrecoUnitario()));
                    } else if (op.getTipoOperacao() == Operacao.TipoOperacao.VENDA) {
                        quantidadeTotal = quantidadeTotal.subtract(op.getQuantidade());
                    }
                }

                if (quantidadeTotal.compareTo(BigDecimal.ZERO) > 0) {
                    PosicaoAtivoDto posicaoDto = new PosicaoAtivoDto();
                    posicaoDto.setCodigoAtivo(ativo.getCodigo());
                    posicaoDto.setQuantidade(quantidadeTotal);
                    BigDecimal precoMedio = calcular(compras);
                    posicaoDto.setPrecoMedio(precoMedio);
                    posicaoDto.setTotalInvestido(quantidadeTotal.multiply(precoMedio));
                    posicoes.add(posicaoDto);
                }
            }

            PosicaoConsolidadaDto resultadoFinal = new PosicaoConsolidadaDto();
            resultadoFinal.setNomeUsuario(usuario.getNome());
            resultadoFinal.setPosicoesPorAtivo(posicoes);
            resultadoFinal.setLucroPrejuizoTotalCarteira(BigDecimal.ZERO); // Placeholder

            return resultadoFinal;
        }
    }
}