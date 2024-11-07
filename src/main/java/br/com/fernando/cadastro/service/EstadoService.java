/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.service;

import br.com.fernando.cadastro.entity.Estado;
import br.com.fernando.cadastro.repository.EstadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fernando
 */
@Service
public class EstadoService {
	
	@Autowired
    private EstadoRepository estadoRepository;

	public List<Estado> listarEstadosPorPais(int idPais) {
		return estadoRepository.listaEstadosPorPais(idPais);
	}
	
	public List<Estado> listarEstados() {
		return estadoRepository.findAll();
	}

	public Estado buscarPorId(Integer id) {
		return estadoRepository.getReferenceById(id);
	}

	public boolean salvar(Estado estado) {
		try{
			estadoRepository.save(estado);
			return true;
		} catch (Throwable e) {
            // Tratar erro, por exemplo, erro de banco de dados
            return false;
        }
	}

	public boolean excluir(Estado estado) {
		try{
			estadoRepository.delete(estado);
			return true;
		} catch (Throwable e) {
            // Tratar erro, por exemplo, erro de banco de dados
            return false;
        }
	}
}
