/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.restController;

import br.com.fernando.cadastro.entity.Categoria;
import br.com.fernando.cadastro.entity.Produto;
import br.com.fernando.cadastro.service.CategoriaService;
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
@RequestMapping("/api/categorias")
public class CategoriaRestController {
	
	@Autowired
    private CategoriaService categoriaService;
	@Autowired
	private ProdutoService produtoService;	
	
	@GetMapping("/")
    public ResponseEntity<List<Categoria>> listarPaises() {
        List<Categoria> lista = categoriaService.listarPaises();

        return ResponseEntity.ok(lista);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCategoria(@PathVariable Integer id) {
		Categoria categoria = categoriaService.buscarPorId(id);
		
		return ResponseEntity.ok(categoria);
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> criarCategoria(@RequestBody Categoria categoria){
		categoria.setId(0);
		
		if (categoriaService.salvar(categoria)){
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/")
    public ResponseEntity<Void> atualizarCategoria(@RequestBody Categoria categoria) {
        if (categoriaService.salvar(categoria)) {
            return ResponseEntity.ok().build(); 
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable int id) {
        Categoria categoria = categoriaService.buscarPorId(id);

        if (categoria != null) {
            List<Produto> produtos = produtoService.listarProdutosPorCategoria(categoria.getId());

            if (!produtos.isEmpty()) {
                // Retorna 440, código não padrão para "não pode ser excluído"
                return ResponseEntity.status(440).body(null); 
            }

            if (categoriaService.excluir(categoria)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}