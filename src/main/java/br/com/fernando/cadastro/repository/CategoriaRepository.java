/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.repository;

import br.com.fernando.cadastro.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author fernando
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
}
