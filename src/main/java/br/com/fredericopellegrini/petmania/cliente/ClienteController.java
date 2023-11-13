package br.com.fredericopellegrini.petmania.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> cadastrarCliente(@RequestBody ClienteModel clienteModel) {
        clienteService.cadastrarCliente(clienteModel);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<ClienteModel>> listarClientes() {
        List<ClienteModel> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteModel> consultarClientePorCpf(@PathVariable String cpf) {
        Optional<ClienteModel> cliente = clienteService.consultarClientePorCpf(cpf);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteModel> editarCliente(@PathVariable String cpf, @RequestBody ClienteModel clienteModel) {
        Optional<ClienteModel> clienteOptional = clienteService.consultarClientePorCpf(cpf);
        if (clienteOptional.isPresent()) {
            clienteService.editarCliente(cpf, clienteModel);
            return ResponseEntity.ok(clienteModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> excluirCliente(@PathVariable String cpf) {
        boolean clienteExcluido = clienteService.excluirCliente(cpf);
        if (clienteExcluido) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content quando o cliente é excluído com sucesso
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found quando o cliente não é encontrado
        }
    }

    

}