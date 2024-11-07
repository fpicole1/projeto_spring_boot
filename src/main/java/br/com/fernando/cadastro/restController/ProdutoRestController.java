/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.restController;

import br.com.fernando.cadastro.entity.Produto;
import br.com.fernando.cadastro.service.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fernando
 */
@RestController
@RequestMapping("/api/produtos")
public class ProdutoRestController {
	
	@Autowired
	private ProdutoService produtoService;	
	
	@GetMapping("/")
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> lista = produtoService.listarProdutos();

        // Retorna a lista ordenada com o status 200 OK
        return ResponseEntity.ok(lista);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProduto(@PathVariable Integer id) {
		Produto produto = produtoService.buscarPorId(id);
		
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> criarProduto(@RequestBody Produto produto){
		produto.setId(0);
		
		if (produtoService.salvar(produto)){
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/")
    public ResponseEntity<Void> atualizarProduto(@RequestBody Produto produto) {
        if (produtoService.salvar(produto)) {
            return ResponseEntity.ok().build(); 
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable int id) {
        Produto produto = produtoService.buscarPorId(id);

        if (produto != null) {
            if (produtoService.excluir(produto)) {
                return ResponseEntity.ok().build(); 
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
