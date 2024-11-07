/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.restController;

import br.com.fernando.cadastro.entity.Cidade;
import br.com.fernando.cadastro.entity.Estado;
import br.com.fernando.cadastro.service.CidadeService;
import br.com.fernando.cadastro.service.EstadoService;
import java.util.Comparator;
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
@RequestMapping("/api/estados")
public class EstadoController {
	
	@Autowired
    private EstadoService estadoService;
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping("/")
    public ResponseEntity<List<Estado>> listarEstados() {
        // Obtém a lista de estados
        List<Estado> lista = estadoService.listarEstados();

        // Compara os estados pelo nome do país e, em seguida, pelo nome do estado
        Comparator<Estado> comparator = Comparator
            .comparing((Estado estado) -> estado.getPais().getNome())
            .thenComparing(Estado::getNome);

        // Ordena a lista com o comparador
        lista.sort(comparator);

        // Retorna a lista ordenada com o status 200 OK
        return ResponseEntity.ok(lista);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> getEstado(@PathVariable Integer id) {
		Estado estado = estadoService.buscarPorId(id);
		
		return ResponseEntity.ok(estado);
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> criarEstado(@RequestBody Estado estado){
		estado.setId(0);
		
		if (estadoService.salvar(estado)){
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/")
    public ResponseEntity<Void> atualizarEstado(@RequestBody Estado estado) {
        // Tente salvar o estado utilizando o serviço
        boolean sucesso = estadoService.salvar(estado);

        if (sucesso) {
            return ResponseEntity.ok().build(); // Retorna 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 Internal Server Error
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEstado(@PathVariable int id) {
        Estado estado = estadoService.buscarPorId(id);

        if (estado != null) {
            // Verificar se o estado possui cidades associadas
            List<Cidade> cidades = cidadeService.listarCidadesPorEstado(estado.getId());

            if (!cidades.isEmpty()) {
                // Retorna 440, código não padrão para "não pode ser excluído"
                return ResponseEntity.status(440).body(null); 
            }

            // Se não houver cidades, tenta excluir o estado
            if (estadoService.excluir(estado)) {
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
