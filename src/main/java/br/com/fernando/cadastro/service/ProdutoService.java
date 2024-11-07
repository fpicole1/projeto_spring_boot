/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.service;

import br.com.fernando.cadastro.entity.Produto;
import br.com.fernando.cadastro.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fernando
 */
@Service
public class ProdutoService {
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Produto buscarPorId(Integer id) {
		return produtoRepository.getReferenceById(id);
	}

	public boolean salvar(Produto produto) {
		try{
			produtoRepository.save(produto);
			return true;
		} catch (Throwable e) {
            // Tratar erro, por exemplo, erro de banco de dados
            return false;
        }
	}

	public boolean excluir(Produto produto) {
		try{
			produtoRepository.delete(produto);
			return true;
		} catch (Throwable e) {
            // Tratar erro, por exemplo, erro de banco de dados
            return false;
        }
	}

	public List<Produto> listarProdutosPorCategoria(int idCategoria) {
		return produtoRepository.listarProdutosPorCategoria(idCategoria);
	}
}
