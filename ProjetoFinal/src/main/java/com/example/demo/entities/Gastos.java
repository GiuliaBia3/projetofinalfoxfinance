package com.example.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gastos")
public class Gastos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_gastos;

	@Column(name = "valor", nullable = false, precision = 8, scale = 2)
	private BigDecimal valor;
	
	@Column(name = "tipo_gastos", nullable = false)
	private String tipo_gastos;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataa", nullable = false)
	private LocalDate dataa;	

	//gastos está puxando a categoria
	@ManyToOne
	@JoinColumn(name= "categorias_gastos")
	private Categorias categoria;

	//gastos está puxando o usuário
	@ManyToOne
	@JoinColumn(name= "usuario_gastos")
	private Usuario usuario;
	
	//GETTERS AND SETTERS

	public Integer getId_gastos() {
		return id_gastos;
	}

	public void setId_gastos(Integer id_gastos) {
		this.id_gastos = id_gastos;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getTipo_gastos() {
		return tipo_gastos;
	}

	public void setTipo_gastos(String tipo_gastos) {
		this.tipo_gastos = tipo_gastos;
	}

	public LocalDate getDataa() {
		return dataa;
	}

	public void setDataa(LocalDate dataa) {
		this.dataa = dataa;
	}

	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Gastos(Integer id_gastos, BigDecimal valor, String tipo_gastos, LocalDate dataa, Categorias categoria,
			Usuario usuario) {
		super();
		this.id_gastos = id_gastos;
		this.valor = valor;
		this.tipo_gastos = tipo_gastos;
		this.dataa = dataa;
		this.categoria = categoria;
		this.usuario = usuario;
	}
	
	public Gastos() {
		
	}
	
	
	
	
	

	
	
}
