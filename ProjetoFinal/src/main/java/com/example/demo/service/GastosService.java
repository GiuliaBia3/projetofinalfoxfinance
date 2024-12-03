package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Gastos;
import com.example.demo.repositories.GastosRepositorio;

import jakarta.transaction.Transactional;

@Service
public class GastosService {
 
	private final GastosRepositorio gastosRepositorio;
	
	@Autowired
	public GastosService(GastosRepositorio gastosRepositorio) {
		this.gastosRepositorio = gastosRepositorio;
	}
	
	//Salvar um gasto
	public Gastos saveGastos(Gastos gastos) {
		return gastosRepositorio.save(gastos);
	}
	
	//Buscar um gasto por ID
	public Gastos getGastosById(Integer id) {
		return gastosRepositorio.findById(id).orElse(null);
	}
	
	//Buscar todos os gastos
	public List<Gastos> getAllGastos(){
		return gastosRepositorio.findAll();
	}
	
	//queryMethod Buscar gastos por usuário
	public List<Gastos> buscarPorUsuarioGastos(Integer usuario_gastos){
		return gastosRepositorio.buscarPorUsuarioGastos(usuario_gastos);
	}
	
	//queryMethod Valor Buscar gastos por valor
	public List<Gastos> buscarPorValorGastos(BigDecimal valor){
		return gastosRepositorio.buscarPorValorGastos(valor);
	}
	
	//queryMethod Buscar gastos por tipo
		public List<Gastos> buscarPorTipo_Gastos(String tipo_gastos){
			return gastosRepositorio.buscarPorTipo_Gastos(tipo_gastos);
	}
	
	//queryMethod Buscar gastos após uma data específica
		public List<Gastos> buscarPorDataaGastos(LocalDate dataa){
			return gastosRepositorio.buscarPorDataaGastos(dataa);
	}
	
	//queryMethod Buscar gastos de um usuário após uma data
		public List<Gastos> buscarPorUsuarioEGastoComData(Integer usuario_gastos, LocalDate dataa) {
	        return gastosRepositorio.buscarPorUsuarioEGastoComData(usuario_gastos, dataa);
	 }
	
	//Deletar um gasto
	public void deleteGastos(Integer id) {
		gastosRepositorio.deleteById(id);
	}
	
  	
	////Métodos que excluem os registros baseados no ID do usuário
    @Transactional
	public void deleteByUsuarioId(Integer usuario_gastos) {
		gastosRepositorio.deleteByUsuarioId(usuario_gastos);
	}

	
	//Atualizar um gasto
	public Gastos updateGastos(Integer id, Gastos novoGastos) {
		Optional<Gastos> gastosOptional = gastosRepositorio.findById(id);
		if (gastosOptional.isPresent()) {
			Gastos gastosExistente = gastosOptional.get();
			gastosExistente.setDataa(novoGastos.getDataa());
			gastosExistente.setValor(novoGastos.getValor());
			gastosExistente.setCategoria(novoGastos.getCategoria());
			gastosExistente.setUsuario(novoGastos.getUsuario());
			gastosExistente.setTipo_gastos(novoGastos.getTipo_gastos());						
			return gastosRepositorio.save(gastosExistente);
		} else {
			return null;
		}
	}

	
}
