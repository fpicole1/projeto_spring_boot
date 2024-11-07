/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.repository;

import br.com.fernando.cadastro.entity.Estado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author fernando
 */
public interface EstadoRepository  extends JpaRepository<Estado, Integer> {

	@Query(value = "select e FROM Estado e INNER JOIN FETCH e.pais p WHERE p.id = :idPais ORDER BY e.nome")
	public List<Estado> listaEstadosPorPais(@Param("idPais") int idPais);
}
