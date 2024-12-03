package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Receita;
import com.example.demo.repositories.ReceitaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ReceitaService {
	private final ReceitaRepositorio receitaRepositorio;

	@Autowired
	public ReceitaService(ReceitaRepositorio receitaRepositorio) {
		this.receitaRepositorio = receitaRepositorio;
	}

	// Salvar uma Receita
	public Receita saveReceita(Receita receita) {
		return receitaRepositorio.save(receita);
	}

	// Buscar Receita por ID
	public Receita getReceitaById(Integer id) {
		return receitaRepositorio.findById(id).orElse(null);
	}

	// Listar todas as Receitas
	public List<Receita> getAllReceita() {
		return receitaRepositorio.findAll();
	}

	// queryMethod Buscar receita por usuário
	public List<Receita> buscarPorUsuarioRenda(Integer receita_usuarios) {
		return receitaRepositorio.buscarPorUsuarioRenda(receita_usuarios);
	}

	// Deletar uma Receita
	public void deleteReceita(Integer id) {
		receitaRepositorio.deleteById(id);
	}

	// Métodos que excluem os registros baseados no ID do usuário
	@Transactional
	public void deleteByUsuarioId(Integer receita_usuarios) {
		receitaRepositorio.deleteByUsuarioId(receita_usuarios);
	}
	

	// Atualizar uma Receita
	public Receita updateReceita(Integer id, Receita novoReceita) {
		Optional<Receita> receitaOptional = receitaRepositorio.findById(id);
		if (receitaOptional.isPresent()) {
			Receita receitaExistente = receitaOptional.get();
			receitaExistente.setRenda(novoReceita.getRenda());
			receitaExistente.setUsuario(novoReceita.getUsuario());
			return receitaRepositorio.save(receitaExistente);
		} else {
			return null;
		}
	}
}