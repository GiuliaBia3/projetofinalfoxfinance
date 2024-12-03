package com.example.demo.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import com.example.demo.entities.Gastos;
import com.example.demo.service.GastosService;

@RestController
@RequestMapping("/gastos")
public class GastosControllers {

	private final GastosService gastosService;

	@Autowired
	public GastosControllers(GastosService gastosService) {
		this.gastosService = gastosService;
	}

	// Método para buscar um gasto por ID
	@GetMapping("/{id}")
	public ResponseEntity<Gastos> findGastosbyId(@PathVariable Integer id) {
		Gastos gastos = gastosService.getGastosById(id);
		if (gastos != null) {
			return ResponseEntity.ok(gastos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Método para listar todos os gastos
	@GetMapping("/all")
	public ResponseEntity<List<Gastos>> findAllGastoscontrol() {
		List<Gastos> gastos = gastosService.getAllGastos();
		return ResponseEntity.ok(gastos);
	}

	// queryMethod Gastos por Usuario
	@GetMapping("/lista/{usuario_gastos}")
	public ResponseEntity<List<Gastos>> buscarPorUsuarioGastos(@PathVariable Integer usuario_gastos) {
		List<Gastos> listadegastos = gastosService.buscarPorUsuarioGastos(usuario_gastos);
		return ResponseEntity.ok(listadegastos);
	}

	// queryMethod gastos por valor
	@GetMapping("/listadegastos/{valor}")
	public ResponseEntity<List<Gastos>> buscarPorValorGastos(@PathVariable BigDecimal valor) {
		List<Gastos> listadegastos = gastosService.buscarPorValorGastos(valor);
		return ResponseEntity.ok(listadegastos);
	}

	// queryMethod gastos por tipo
	@GetMapping("/listaTipo_Gastos/{tipo_gastos}")
	public ResponseEntity<List<Gastos>> buscarPorTipo_Gastos(@PathVariable String tipo_gastos) {
		List<Gastos> listadegastos = gastosService.buscarPorTipo_Gastos(tipo_gastos);
		return ResponseEntity.ok(listadegastos);
	}

	// queryMethod gastos após uma data específica
	@GetMapping("/listadegastospordata/{dataa}")
	public ResponseEntity<List<Gastos>> buscarPorDataaGastos(@PathVariable LocalDate dataa) {
		List<Gastos> listadegastos = gastosService.buscarPorDataaGastos(dataa);
		return ResponseEntity.ok(listadegastos);
	}

	// queryMethod gastos por usuário e data
	@GetMapping("/buscarPorUsuarioEData/{usuario_gastos}/{dataa}")
	public List<Gastos> buscarPorUsuarioEGastoComData(@PathVariable Integer usuario_gastos,
			@PathVariable LocalDate dataa) {
		return gastosService.buscarPorUsuarioEGastoComData(usuario_gastos, dataa);
	}

	// Criar um novo gasto
	@PostMapping("/criar")
	public ResponseEntity<Gastos> insertGastosControl(@RequestBody Gastos gastos) {
		Gastos novogastos = gastosService.saveGastos(gastos);
		return ResponseEntity.status(HttpStatus.CREATED).body(novogastos);
	}

	// Atualizar um gasto existente
	@PutMapping("/{id}")
	public Gastos updateGastos(@PathVariable Integer id, @RequestBody Gastos gastos) {
		return gastosService.updateGastos(id, gastos);
	}

	// Deletar um gasto
	@DeleteMapping("/{id}")
	public void deleteGastos(@PathVariable Integer id) {
		gastosService.deleteGastos(id);
	}
}
