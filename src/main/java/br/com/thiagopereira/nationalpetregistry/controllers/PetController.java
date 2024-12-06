package br.com.thiagopereira.nationalpetregistry.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagopereira.nationalpetregistry.models.Pet;
import br.com.thiagopereira.nationalpetregistry.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/pets")
@Tag(name = "Pets")
public class PetController {

  @Autowired
  private final PetService petService;

  public PetController(
      PetService petService) {
    this.petService = petService;
  }

  @GetMapping
  @Operation(summary = "Find all pets", description = "Find all pets.")
  public ResponseEntity<List<Pet>> findAll() {
    return ResponseEntity.ok(petService.findAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Find pet by id", description = "Find pet by id.")
  public ResponseEntity<Pet> findById(@PathVariable UUID id) {
    return ResponseEntity.ok(petService.findByid(id));
  }

  @PostMapping
  @Operation(summary = "Store pet", description = "Store pet.")
  public ResponseEntity<Pet> store(@RequestBody Pet pet) {
    return ResponseEntity.ok(petService.store(pet));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update pet", description = "Update pet.")
  public ResponseEntity<Pet> update(@PathVariable UUID id, @RequestBody Pet updatedPet) {
    return ResponseEntity.ok(petService.update(id, updatedPet));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete pet", description = "Delete pet.")
  public void delete(@PathVariable UUID id) {
    petService.delete(id);
  }

}
