package net.classnotfound.pet.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.classnotfound.pet.domain.Pet;
import net.classnotfound.pet.service.PetService;
import net.classnotfound.pet.web.consersion.PetConverter;
import net.classnotfound.pet.web.json.JsonPet;

@RestController
public class PetController {
	
	@Autowired
	private PetService petService;
	
	/**
	 * Find all the pet in the database
	 * @return A collection of pets
	 */
	@RequestMapping(value = "/pet/all", method = RequestMethod.GET)
	@ResponseBody
	public Collection<JsonPet> findAll() {
		Collection<Pet> entity = petService.findAll();
		Collection<JsonPet> jsonObject = new PetConverter().fromEntityList(entity);
		return jsonObject;
	}
	
	/**
	 * Get the detail of a pet
	 * @param id the id of the pet
	 * @return the detail of the pet with the corresponding id
	 */
	@Transactional(propagation= Propagation.SUPPORTS)
	@RequestMapping(value = "/pet/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonPet find(@PathVariable("id") final Integer id) {
		Pet entity = petService.findById(id);
		JsonPet jsonObject = new PetConverter().fromEntity(entity);
		return jsonObject;
	}

	/**
	 * Save the pet in the database
	 * @param jsonPet the pet to be recorded
	 * @return the pet with its generated id
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	@RequestMapping(value = "/pet", method = RequestMethod.POST, consumes = {"application/json" })
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody JsonPet save(@RequestBody final JsonPet jsonPet) {
		Pet pet = new PetConverter().fromJson(jsonPet);
		petService.save(pet);
		JsonPet createdjson = new PetConverter().fromEntity(pet);
		return createdjson;
	}

}
