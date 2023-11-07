package br.com.fredericopellegrini.petmania.pet;

import org.springframework.stereotype.Service;

import br.com.fredericopellegrini.petmania.cliente.ClienteModel;
import br.com.fredericopellegrini.petmania.cliente.ClienteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final List<PetModel> pets = new ArrayList<>();
    private long idCounter = 1; // Contador para gerar IDs únicos
    private final ClienteService clienteService;

    public PetService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void cadastrarPet(PetModel pet) {
        pet.setId(idCounter++); // Define o ID e depois incrementa o contador
        pets.add(pet);
    }

    public List<PetModel> listarPets() {
        return pets;
    }

    public Optional<PetModel> consultarPetPorId(Long id) {
        return pets.stream()
                .filter(pet -> pet.getId().equals(id))
                .findFirst();
    }

    public void editarPet(Long id, PetModel petAtualizado) {
        for (int i = 0; i < pets.size(); i++) {
            PetModel pet = pets.get(i);
            if (pet.getId().equals(id)) {
                pet.setNome(petAtualizado.getNome());
                pet.setEspecie(petAtualizado.getEspecie());
                pet.setRaca(petAtualizado.getRaca());
                pet.setIdade(petAtualizado.getIdade());
                // Atualizar outras informações do pet, se necessário
                pets.set(i, pet);
                break;
            }
        }
    }

    public void excluirPet(Long id) {
        pets.removeIf(pet -> pet.getId().equals(id));
    }

    public boolean associarPetAoCliente(Long petId, String clienteCpf) {
        Optional<PetModel> petOptional = consultarPetPorId(petId);
        Optional<ClienteModel> clienteOptional = clienteService.consultarClientePorCpf(clienteCpf);
    
        if (petOptional.isPresent() && clienteOptional.isPresent()) {
            PetModel pet = petOptional.get();
            ClienteModel cliente = clienteOptional.get();
    
            // Inicialize a lista de pets do cliente se ainda não estiver inicializada
            if (cliente.getPets() == null) {
                cliente.setPets(new ArrayList<>());
            }
    
            // Associe o pet ao cliente e adicione-o à lista de pets do cliente
            pet.setClienteCpf(clienteCpf);
            editarPet(petId, pet);
            cliente.adicionarPet(pet);
    
            return true; // Associação bem-sucedida
        }
        return false; // Pet ou cliente não encontrado
    }
}
