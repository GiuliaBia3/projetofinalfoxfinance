package com.example.demo.entities;

import java.math.BigDecimal;
import java.util.List;

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
@Table(name = "receita")
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_receita;

	@Column(name = "renda", nullable = false, precision = 8, scale = 2)
	private BigDecimal renda;
	
	//receita está puxando o usuário
	@ManyToOne
	@JoinColumn(name= "receita_usuarios")
	private Usuario usuario;

	
	//GETTERS AND SETTERS
	
	public Integer getId_receita() {
		return id_receita;
	}

	public void setId_receita(Integer id_receita) {
		this.id_receita = id_receita;
	}

	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
}
