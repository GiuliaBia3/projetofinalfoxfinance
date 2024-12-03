package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Categorias;
import com.example.demo.entities.Receita;
import com.example.demo.repositories.CategoriaRepositorio;
import com.example.demo.repositories.ReceitaRepositorio;

@Service
public class CategoriaService {
	private final CategoriaRepositorio categoriaRepositorio;

	@Autowired
	public CategoriaService(CategoriaRepositorio categoriaRepositorio) {
		this.categoriaRepositorio = categoriaRepositorio;
	}

	//Salvar uma Categoria
	public Categorias saveCategorias(Categorias categorias) {
		return categoriaRepositorio.save(categorias);
	}

	//Buscar Categoria por ID
	public Categorias getCategoriasById(Integer id) {
		return categoriaRepositorio.findById(id).orElse(null);
	}

	//Listar todas as Categorias
	public List<Categorias> getAllCategorias() {
		return categoriaRepositorio.findAll();
	}

	//Deletar uma Categoria
	public void deleteCategorias(Integer id) {
		categoriaRepositorio.deleteById(id);
	}

	//Atualizar uma Categoria
	// fazendo o update do jogo com o optional
	public Categorias updateCategorias(Integer id, Categorias novoCategorias) {
		Optional<Categorias> categoriaOptional = categoriaRepositorio.findById(id);
		if (categoriaOptional.isPresent()) {
			Categorias categoriaExistente = categoriaOptional.get();
			categoriaExistente.setTipo(novoCategorias.getTipo());
			return categoriaRepositorio.save(categoriaExistente);
		} else {
			return null;
		}
	}

}
