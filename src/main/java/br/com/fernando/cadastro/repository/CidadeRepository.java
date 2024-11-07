/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.repository;

import br.com.fernando.cadastro.entity.Cidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author fernando
 */
public interface CidadeRepository  extends JpaRepository<Cidade, Integer> {

	@Query(value = "select c FROM Cidade c INNER JOIN FETCH c.estado e WHERE e.id = :idEstado ORDER BY c.nome")
	public List<Cidade> listarCidadesPorEstado(@Param("idEstado") int idEstado);
	
}
