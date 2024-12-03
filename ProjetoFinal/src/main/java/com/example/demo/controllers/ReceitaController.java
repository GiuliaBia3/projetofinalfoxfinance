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

import com.example.demo.entities.Receita;
import com.example.demo.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	private final ReceitaService receitaService;

	@Autowired
	public ReceitaController(ReceitaService receitaService) {
		this.receitaService = receitaService;
	}

	// Buscar Receita por ID
	@GetMapping("/{id}")
	public ResponseEntity<Receita> findReceitabyId(@PathVariable Integer id) {
		Receita receita = receitaService.getReceitaById(id);
		if (receita != null) {
			return ResponseEntity.ok(receita);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Listar todas as Receitas
	@GetMapping("/all")
	public ResponseEntity<List<Receita>> findAllUsuarioscontrol() {
		List<Receita> receita = receitaService.getAllReceita();
		return ResponseEntity.ok(receita);
	}

	// queryMethod Gastos por Usuario
	@GetMapping("/listaReceita/{receita_usuarios}")
	public ResponseEntity<List<Receita>> buscarPorUsuarioRenda(@PathVariable Integer receita_usuarios) {
		List<Receita> listaderenda = receitaService.buscarPorUsuarioRenda(receita_usuarios);
		return ResponseEntity.ok(listaderenda);
	}

	// Criar uma Receita
	@PostMapping("/criar")
	public ResponseEntity<Receita> insertUsuariosControl(@RequestBody Receita receita) {
		Receita novoreceita = receitaService.saveReceita(receita);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoreceita);
	}

	// Atualizar uma Receita
	@PutMapping("/{id}")
	public Receita updateReceita(@PathVariable Integer id, @RequestBody Receita receita) {
		return receitaService.updateReceita(id, receita);
	}

	// Deletar uma Receita
	@DeleteMapping("/{id}")
	public void deleteReceita(@PathVariable Integer id) {
		receitaService.deleteReceita(id);
	}

}
