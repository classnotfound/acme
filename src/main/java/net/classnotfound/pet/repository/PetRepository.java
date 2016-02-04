package net.classnotfound.pet.repository;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import net.classnotfound.pet.domain.Pet;

public interface PetRepository extends CrudRepository<Pet, Integer>, Repository<Pet, Integer> {

	Pet findByName(String name);

	Pet findById(Integer id);

	Collection<Pet> findAll(Sort sort);

}
