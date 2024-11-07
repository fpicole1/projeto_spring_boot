/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.restController;

import br.com.fernando.cadastro.entity.Cidade;
import br.com.fernando.cadastro.service.CidadeService;
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
@RequestMapping("/api/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping("/")
    public ResponseEntity<List<Cidade>> listarCidades() {
        // Obtém a lista de estados
        List<Cidade> lista = cidadeService.listarCidades();

        // Retorna a lista ordenada com o status 200 OK
        return ResponseEntity.ok(lista);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> getCidade(@PathVariable Integer id) {
		Cidade cidade = cidadeService.buscarPorId(id);
		
		return ResponseEntity.ok(cidade);
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> criarCidade(@RequestBody Cidade cidade){
		cidade.setId(0);
		
		if (cidadeService.salvar(cidade)){
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/")
    public ResponseEntity<Void> atualizarCidade(@RequestBody Cidade cidade) {
        // Tente salvar o estado utilizando o serviço
        boolean sucesso = cidadeService.salvar(cidade);

        if (sucesso) {
            return ResponseEntity.ok().build(); // Retorna 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 Internal Server Error
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEstado(@PathVariable int id) {
        Cidade cidade = cidadeService.buscarPorId(id);

        if (cidade != null) {
            // Se não houver cidades, tenta excluir o estado
            if (cidadeService.excluir(cidade)) {
                return ResponseEntity.ok().build(); // Retorna 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 Internal Server Error
            }
            
        } else {
            // Se o estado não for encontrado, retorna 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}