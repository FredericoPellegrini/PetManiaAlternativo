package br.com.fredericopellegrini.petmania.cliente;

import br.com.fredericopellegrini.petmania.pet.PetModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClienteModel {
    private String nome;
    private String cpf;
    private String endereco;
    private List<PetModel> pets; // Lista de pets associados ao cliente

    // Construtor para inicializar a lista de pets como uma nova lista vazia
    public ClienteModel() {
        this.pets = new ArrayList<>();
    }

    public void adicionarPet(PetModel pet) {
        pets.add(pet);
    }

    
}