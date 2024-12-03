package com.example.demo.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.entities.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	//Método personalizado: buscarPorUsuarioId
	@Query(value = "select * from usuario u where u.id_usuario = :id_usuario order by u.id_usuario;", nativeQuery = true)
	List<Usuario> buscarPorUsuarioId(@Param("id_usuario") Integer id_usuario);
	
	//Método personalizado: buscarPorNomeUsuario
	@Query ("SELECT l FROM Usuario l WHERE l.nome = ?1")
	List<Usuario> buscarPorNomeUsuario(String nome);
}
