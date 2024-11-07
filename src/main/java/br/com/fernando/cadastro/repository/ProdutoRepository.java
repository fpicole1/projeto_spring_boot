/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.repository;

import br.com.fernando.cadastro.entity.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author fernando
 */
public interface ProdutoRepository  extends JpaRepository<Produto, Integer> {

	@Query(value = "select p FROM Produto p INNER JOIN FETCH p.categoria c WHERE c.id = :idCategoria ORDER BY p.nome")
	public List<Produto> listarProdutosPorCategoria(@Param("idCategoria") int idCategoria);
	
}
