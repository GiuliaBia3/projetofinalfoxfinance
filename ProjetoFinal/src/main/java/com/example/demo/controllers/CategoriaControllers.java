package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entities.Categorias;
import com.example.demo.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaControllers {

	private final CategoriaService categoriaService;

	@Autowired
	public CategoriaControllers(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	// Buscar Categoria por ID
	@GetMapping("/{id}")
	public ResponseEntity<Categorias> findCategoriabyId(@PathVariable Integer id) {
		Categorias categoria = categoriaService.getCategoriasById(id);
		if (categoria != null) {
			return ResponseEntity.ok(categoria);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Listar todas as Categorias
	@GetMapping("/all")
	public ResponseEntity<List<Categorias>> findAllCategoriacontrol() {
		List<Categorias> categoria = categoriaService.getAllCategorias();
		return ResponseEntity.ok(categoria);
	}

	// Criar uma nova Categoria
	@PostMapping("/criar")
	public ResponseEntity<Categorias> insertUsuariosControl(@RequestBody Categorias categoria) {
		Categorias novocategoria = categoriaService.saveCategorias(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(novocategoria);
	}

	// Atualizar uma Categoria
	@PutMapping("/{id}")
	public Categorias updateCategorias(@PathVariable Integer id, @RequestBody Categorias categoria) {
		return categoriaService.updateCategorias(id, categoria);
	}

	// Deletar uma Categoria
	@DeleteMapping("/{id}")
	public void deleteCategorias(@PathVariable Integer id) {
		categoriaService.deleteCategorias(id);
	}

}
