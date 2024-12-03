package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Usuario;
import com.example.demo.service.GastosService;
import com.example.demo.service.ReceitaService;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControllers {

	private final UsuarioService usuarioService;
	private final GastosService gastosService;
	private final ReceitaService receitaService;

	@Autowired
	public UsuarioControllers(UsuarioService usuarioService, ReceitaService receitaService,
			GastosService gastosService) {
		this.usuarioService = usuarioService;
		this.gastosService = gastosService;
		this.receitaService = receitaService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findUsuariobyId(@PathVariable Integer id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> findAllUsuarioscontrol() {
		List<Usuario> usuario = usuarioService.getAllUsuario();
		return ResponseEntity.ok(usuario);
	}

	// queryMethod Usuario ID
	@GetMapping("/userID/{id_usuario}")
	public ResponseEntity<List<Usuario>> buscarPorUsuarioId(@PathVariable Integer id_usuario) {
		List<Usuario> listaUsers = usuarioService.buscarPorUsuarioId(id_usuario);
		return ResponseEntity.ok(listaUsers);
	}

	// queryMethod Usuario Nome
	@GetMapping("/userNome/{nome}")
	public ResponseEntity<List<Usuario>> buscarPorNomeUsuario(@PathVariable String nome) {
		List<Usuario> listaUsers = usuarioService.buscarPorNomeUsuario(nome);
		return ResponseEntity.ok(listaUsers);
	}

	@PostMapping("/criar")
	public ResponseEntity<Usuario> insertUsuariosControl(@RequestBody Usuario usuario) {
		Usuario novousuario = usuarioService.saveUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(novousuario);
	}

	@PutMapping("/{id}")
	public Usuario updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
		return usuarioService.updateUsuario(id, usuario);
	}

	// Função para deletar o usuário e seus dados relacionados
	@DeleteMapping("/deletar/{id_usuario}")
	public ResponseEntity<Void> deleteUsuarioComDados(@PathVariable Integer id_usuario) {
		try {
			// Excluir receitas do usuário
			receitaService.deleteByUsuarioId(id_usuario);

			// Excluir gastos do usuário
			gastosService.deleteByUsuarioId(id_usuario);

			// Excluir o próprio usuário
			usuarioService.deleteUsuario(id_usuario);

			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
