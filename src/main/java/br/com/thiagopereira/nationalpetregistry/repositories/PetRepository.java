package br.com.thiagopereira.nationalpetregistry.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thiagopereira.nationalpetregistry.models.Pet;

public interface PetRepository extends JpaRepository<Pet, UUID> {
}
