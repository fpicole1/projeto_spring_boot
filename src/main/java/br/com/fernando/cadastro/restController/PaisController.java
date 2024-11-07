/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fernando.cadastro.restController;

//import br.com.fernando.cadastro.entity.Estado;
import br.com.fernando.cadastro.entity.Estado;
import br.com.fernando.cadastro.entity.Pais;
import br.com.fernando.cadastro.service.EstadoService;
import br.com.fernando.cadastro.service.PaisService;
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
@RequestMapping("/api/paises")
public class PaisController {
	
	@Autowired
	private PaisService paisService;
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping("/")
    public ResponseEntity<List<Pais>> listarPaises() {
        // Obtém a lista de estados
        List<Pais> lista = paisService.listarPaises();

        // Compara os estados pelo nome do país e, em seguida, pelo nome do estado
        Comparator<Pais> comparator = Comparator
            .comparing((Pais pais) -> pais.getNome());

        // Ordena a lista com o comparador
        lista.sort(comparator);

        // Retorna a lista ordenada com o status 200 OK
        return ResponseEntity.ok(lista);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Pais> getPais(@PathVariable Integer id) {
		Pais pais = paisService.buscarPorId(id);
		
		return ResponseEntity.ok(pais);
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> criarPais(@RequestBody Pais pais){
		pais.setId(0);
		
		if (paisService.salvar(pais)){
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/")
    public ResponseEntity<Void> atualizarPais(@RequestBody Pais pais) {
        // Tente salvar o estado utilizando o serviço
        boolean sucesso = paisService.salvar(pais);

        if (sucesso) {
            return ResponseEntity.ok().build(); // Retorna 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 Internal Server Error
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEstado(@PathVariable int id) {
        Pais pais = paisService.buscarPorId(id);

        if (pais != null) {
            // Verificar se o estado possui cidades associadas
            List<Estado> estados = estadoService.listarEstadosPorPais(pais.getId());

            if (!estados.isEmpty()) {
                // Retorna 440, código não padrão para "não pode ser excluído"
                return ResponseEntity.status(440).body(null); 
            }

            // Se não houver cidades, tenta excluir o estado
            if (paisService.excluir(pais)) {
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
