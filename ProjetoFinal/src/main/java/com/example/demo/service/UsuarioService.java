package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepositorio;

@Service
public class UsuarioService {

	private final UsuarioRepositorio usuarioRepositorio;

	@Autowired
	public UsuarioService(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}

	//Salvar um usuário
	public Usuario saveUsuario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}

	//Buscar usuário por ID
	public Usuario getUsuarioById(Integer id) {
		return usuarioRepositorio.findById(id).orElse(null);
	}
	
	//Buscar todos os usuários
	public List<Usuario> getAllUsuario() {
		return usuarioRepositorio.findAll();
	}

	//Deletar um usuário
	public void deleteUsuario(Integer id) {
		usuarioRepositorio.deleteById(id);
	}
	
	//queryMethod Buscar usuário por ID (consulta personalizada)
		public List<Usuario> buscarPorUsuarioId(Integer id_usuario){
			return usuarioRepositorio.buscarPorUsuarioId(id_usuario);
	}
		
	//queryMethod Buscar usuário por nome (consulta personalizada)
		public List<Usuario> buscarPorNomeUsuario(String nome){
			return usuarioRepositorio.buscarPorNomeUsuario(nome);
	}
	

	// fazendo o update do usuario com o optional
	//Atualizar um usuário existente
	public Usuario updateUsuario(Integer id, Usuario novoUsuario) {
		Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
		if (usuarioOptional.isPresent()) {
			Usuario usuarioExistente = usuarioOptional.get();
			usuarioExistente.setEmail(novoUsuario.getEmail());
			usuarioExistente.setNome(novoUsuario.getNome());
			usuarioExistente.setObjetivo(novoUsuario.getObjetivo());
			usuarioExistente.setSenha(novoUsuario.getSenha());
			return usuarioRepositorio.save(usuarioExistente);
		} else {
			return null;
		}
	}

}
