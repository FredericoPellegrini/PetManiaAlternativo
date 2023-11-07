package br.com.fredericopellegrini.petmania.pet;

import lombok.Data;

@Data
public class PetModel {
    private Long id; // Identificador único do pet
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private String clienteCpf;
}
