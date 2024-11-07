/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.service;

import br.com.fernando.cadastro.entity.Cidade;
import br.com.fernando.cadastro.repository.CidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fernando
 */
@Service
public class CidadeService {
	
	@Autowired
    private CidadeRepository cidadeRepository;
	
	public List<Cidade> listarCidadesPorEstado(int idEstado) {
		return cidadeRepository.listarCidadesPorEstado(idEstado);
	}
	
	public List<Cidade> listarCidades() {
		return cidadeRepository.findAll();
	}

	public Cidade buscarPorId(Integer id) {
		return cidadeRepository.getReferenceById(id);
	}

	public boolean salvar(Cidade cidade) {
		try{
			cidadeRepository.save(cidade);
			return true;
		} catch (Throwable e) {
            // Tratar erro, por exemplo, erro de banco de dados
            return false;
        }
	}

	public boolean excluir(Cidade cidade) {
		try{
			cidadeRepository.delete(cidade);
			return true;
		} catch (Throwable e) {
            // Tratar erro, por exemplo, erro de banco de dados
            return false;
        }
	}

	

	
}
