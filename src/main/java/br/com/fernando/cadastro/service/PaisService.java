/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.service;

import br.com.fernando.cadastro.entity.Pais;
import br.com.fernando.cadastro.repository.PaisRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fernando
 */
@Service
public class PaisService {
	
	@Autowired
    private PaisRepository paisRepository;

	public List<Pais> listarPaises() {
		return paisRepository.findAll();
	}

	public Pais buscarPorId(Integer id) {
		return paisRepository.getReferenceById(id);
	}

	public boolean salvar(Pais pais) {
		try{
			paisRepository.save(pais);
			return true;
		} catch (Throwable e) {
            // Tratar erro, por exemplo, erro de banco de dados
            return false;
        }
	}

	public boolean excluir(Pais pais) {
		try{
			paisRepository.delete(pais);
			return true;
		} catch (Throwable e) {
            // Tratar erro, por exemplo, erro de banco de dados
            return false;
        }
	}
}
