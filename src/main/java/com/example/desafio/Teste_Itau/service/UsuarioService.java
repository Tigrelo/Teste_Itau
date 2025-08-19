package com.example.desafio.Teste_Itau.service;

import com.example.desafio.Teste_Itau.entity.Usuario;
import com.example.desafio.Teste_Itau.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        // O findById retorna um "Optional", que é uma forma de evitar erros de valor nulo.
        // O orElseThrow() lança uma exceção se o usuário não for encontrado.
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + id));
    }
}