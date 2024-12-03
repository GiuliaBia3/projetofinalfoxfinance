package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Receita;

public interface ReceitaRepositorio extends JpaRepository<Receita, Integer> {

	// Método para buscar todos os gastos de um usuário específico.
	@Query(value = "select * from receita r where r.receita_usuarios = :receita_usuarios order by r.receita_usuarios;", nativeQuery = true)
	List<Receita> buscarPorUsuarioRenda(@Param("receita_usuarios") Integer receita_usuarios);
	
	@Modifying
	@Query(value = "DELETE FROM receita r WHERE r.receita_usuarios = :receita_usuarios", nativeQuery = true)
	void deleteByUsuarioId(@Param("receita_usuarios") Integer receita_usuarios);

}
