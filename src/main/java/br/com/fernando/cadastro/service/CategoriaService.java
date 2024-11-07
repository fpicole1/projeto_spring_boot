/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.service;

import br.com.fernando.cadastro.entity.Categoria;
import br.com.fernando.cadastro.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fernando
 */
@Service
public class CategoriaService {
	
	@Autowired
    private CategoriaRepository categoriaRepository;

	public List<Categoria> listarPaises() {
		return categoriaRepository.findAll();
	}

	public Categoria buscarPorId(Integer id) {
		return categoriaRepository.getReferenceById(id);
	}

	public boolean salvar(Categoria categoria) {
		try{
			categoriaRepository.save(categoria);
			return true;
		} catch (Throwable e) {
            // Tratar erro, por exemplo, erro de banco de dados
            return false;
        }
	}

	public boolean excluir(Categoria categoria) {
		try{
			categoriaRepository.delete(categoria);
			return true;
		} catch (Throwable e) {
            // Tratar erro, por exemplo, erro de banco de dados
            return false;
        }
	}
}
