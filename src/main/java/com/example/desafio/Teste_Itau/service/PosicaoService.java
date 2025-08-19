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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service // Essencial para o Spring reconhecer esta classe como um serviço
public class PosicaoService {

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
            List<CalculadoraPrecoMedio.Compra> compras = new ArrayList<>();

            for (Operacao op : operacoesDoAtivo) {
                if (op.getTipoOperacao() == Operacao.TipoOperacao.COMPRA) {
                    quantidadeTotal = quantidadeTotal.add(op.getQuantidade());
                    compras.add(new CalculadoraPrecoMedio.Compra(op.getQuantidade(), op.getPrecoUnitario()));
                } else if (op.getTipoOperacao() == Operacao.TipoOperacao.VENDA) {
                    quantidadeTotal = quantidadeTotal.subtract(op.getQuantidade());
                }
            }

            if (quantidadeTotal.compareTo(BigDecimal.ZERO) > 0) {
                PosicaoAtivoDto posicaoDto = new PosicaoAtivoDto();
                posicaoDto.setCodigoAtivo(ativo.getCodigo());
                posicaoDto.setQuantidade(quantidadeTotal);
                BigDecimal precoMedio = CalculadoraPrecoMedio.calcular(compras);
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