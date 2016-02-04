package net.classnotfound.pet.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.classnotfound.pet.domain.Pet;
import net.classnotfound.pet.repository.PetRepository;
import net.classnotfound.pet.validator.PetValidator;

@Service
public class PetServiceImpl implements PetService {
	
	@Autowired
	private PetRepository petRepository;

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void save(Pet pet) {
		PetValidator validator = new PetValidator();
		if (validator.valid()) {
			petRepository.save(pet);
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void deleteById(Integer id) {
		petRepository.delete(id);
		
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Pet findByName(String name) {
		return petRepository.findByName(name);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Collection<Pet> findAll() {
		
		return (Collection<Pet>) petRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Pet findById(Integer id) {
		return petRepository.findById(id);
	}
}
