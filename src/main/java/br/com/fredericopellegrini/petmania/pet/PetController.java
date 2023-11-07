package br.com.fredericopellegrini.petmania.pet;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    // Endpoint para associar um pet a um cliente
    @PostMapping("/{petId}/associar/{clienteCpf}")
    public ResponseEntity<Void> associarPetAoCliente(@PathVariable Long petId, @PathVariable String clienteCpf) {
        boolean associacaoBemSucedida = petService.associarPetAoCliente(petId, clienteCpf);
        if (associacaoBemSucedida) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build(); // Pet ou cliente não encontrado
        }
    }

    @PostMapping("/")
    public void cadastrarPet(@RequestBody PetModel petModel) {
        petService.cadastrarPet(petModel);
    }

    @GetMapping("/")
    public List<PetModel> listarPets() {
        return petService.listarPets();
    }

    @GetMapping("/{id}")
    public PetModel consultarPetPorId(@PathVariable Long id) {
        return petService.consultarPetPorId(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
    }

    @PutMapping("/{id}")
    public void editarPet(@PathVariable Long id, @RequestBody PetModel petModel) {
        petService.editarPet(id, petModel);
    }

    @DeleteMapping("/{id}")
    public void excluirPet(@PathVariable Long id) {
        petService.excluirPet(id);
    }
}
