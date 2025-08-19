package com.example.desafio.Teste_Itau.repository;

import com.example.desafio.Teste_Itau.entity.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {


    List<Operacao> findByUsuarioId(Long usuarioId);

}