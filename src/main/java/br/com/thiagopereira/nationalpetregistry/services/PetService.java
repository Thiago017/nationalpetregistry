package br.com.thiagopereira.nationalpetregistry.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagopereira.nationalpetregistry.models.Pet;
import br.com.thiagopereira.nationalpetregistry.repositories.PetRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PetService {

  @Autowired
  private final PetRepository petRepository;

  public PetService(
      PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  public List<Pet> findByOwnerId(UUID id) {
    return petRepository.findByOwnerId(id);
  }

  public List<Pet> findAll() {
    return petRepository.findAll();
  }

  public Pet findByid(UUID id) {
    return petRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pet not found"));
  }

  public Pet store(Pet pet) {
    return petRepository.save(pet);
  }

  public Pet update(UUID id, Pet updatedPet) {
    Pet pet = findByid(id);
    pet.setId(updatedPet.getId());
    pet.setName(updatedPet.getName());
    pet.setBreed(updatedPet.getBreed());
    pet.setGender(updatedPet.getGender());
    pet.setDateOfBirth(updatedPet.getDateOfBirth());
    pet.setIdentificationDocument(updatedPet.getIdentificationDocument());
    pet.setOwner(updatedPet.getOwner());
    return petRepository.save(pet);
  }

  public void delete(UUID id) {
    Pet pet = findByid(id);
    petRepository.delete(pet);
  }

}
