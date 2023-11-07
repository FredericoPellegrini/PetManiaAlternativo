package br.com.fredericopellegrini.petmania.cliente;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final List<ClienteModel> clientes = new ArrayList<>();

    public boolean cadastrarCliente(ClienteModel cliente) {
        return clientes.add(cliente);
    }

    public List<ClienteModel> listarClientes() {
        return clientes;
    }

    public Optional<ClienteModel> consultarClientePorCpf(String cpf) {
        return clientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst();
    }

    public void editarCliente(String cpf, ClienteModel clienteAtualizado) {
        // Implemente a lógica para editar o cliente
    }

    public boolean excluirCliente(String cpf) {
        Optional<ClienteModel> cliente = consultarClientePorCpf(cpf);
        if (cliente.isPresent()) {
            clientes.remove(cliente.get());
            return true; // Cliente excluído com sucesso
        }
        return false; // Cliente não encontrado
    }
}
