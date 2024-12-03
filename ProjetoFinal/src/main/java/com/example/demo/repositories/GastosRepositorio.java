package com.example.demo.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.entities.Gastos;

public interface GastosRepositorio extends JpaRepository<Gastos, Integer> {

	 // Método para buscar todos os gastos de um usuário específico.
	@Query(value = "select * from gastos g where g.usuario_gastos = :usuario_gastos order by g.usuario_gastos;", nativeQuery = true)
	List<Gastos> buscarPorUsuarioGastos(@Param("usuario_gastos") Integer usuario_gastos);
	
	 // Método para buscar todos os gastos de um usuário que ocorreram após uma data específica.
    @Query(value = "select * from gastos g where g.usuario_gastos = :usuario_gastos and g.dataa > :dataa order by g.usuario_gastos;", nativeQuery = true)
	List<Gastos> buscarPorUsuarioEGastoComData(@Param("usuario_gastos") Integer usuario_gastos, @Param("dataa") LocalDate dataa);

	// Método para buscar todos os gastos que possuem um valor específico.
	@Query ("SELECT l FROM Gastos l WHERE l.valor = ?1")
	List<Gastos> buscarPorValorGastos(BigDecimal valor);
	
	// Método para buscar todos os gastos de um tipo específico.
	@Query ("SELECT l FROM Gastos l WHERE l.tipo_gastos = ?1")
	List<Gastos> buscarPorTipo_Gastos(String tipo_gastos);
	
	// Método para buscar todos os gastos cuja data seja posterior a uma data específica.
	@Query ("SELECT l FROM Gastos l WHERE l.dataa > ?1")
	List<Gastos> buscarPorDataaGastos(LocalDate dataa);
	
	@Modifying
	@Query("DELETE FROM Gastos g WHERE g.usuario.id = :usuario_gastos")
	void deleteByUsuarioId(@Param("usuario_gastos") Integer usuario_gastos);


	
}
