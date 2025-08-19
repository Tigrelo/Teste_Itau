package com.example.desafio.Teste_Itau.repository;

import com.example.desafio.Teste_Itau.entity.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
    // A mágica do JpaRepository acontece aqui.
    // O importante é que a linha acima usa o 'Ativo' importado corretamente.
}