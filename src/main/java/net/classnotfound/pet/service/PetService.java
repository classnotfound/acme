package net.classnotfound.pet.service;

import java.util.Collection;

import net.classnotfound.pet.domain.Pet;

public interface PetService {
	
	//add
	void save(Pet pet);
	//delete
	void deleteById(Integer id);
//	void update(Pet pet);
	//find/view
	Pet findByName(String name);
	
	Pet findById(Integer id);
	
	Collection<Pet> findAll();
	
}
