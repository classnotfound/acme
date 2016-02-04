package net.classnotfound.pet.test.repository;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.classnotfound.pet.Application;
import net.classnotfound.pet.domain.Pet;
import net.classnotfound.pet.repository.PetRepository;
import net.classnotfound.pet.utils.ItemCounter;
import net.classnotfound.pet.utils.builder.PetBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Transactional
public class TestPetRepository {

	@Autowired
	private PetRepository petRepository;
	
	@Test
	public void testFindAll() {
		
		Iterable<Pet> fullList = petRepository.findAll();
		Assert.assertNotNull("List of pet was null", fullList);
		Assert.assertTrue("Number of pets is wrong", new ItemCounter<Pet>(fullList).size()>=6);
	}
	
	@Test
	public void testFind() {
		
		Pet mickey = petRepository.findOne(1);
		Assert.assertNotNull("Mickey is missing", mickey);
		Assert.assertEquals("Mickey is not Mickey", "Mickey", mickey.getName());
		Assert.assertNotNull("Category is missing", mickey.getCategory());
		Assert.assertEquals("Mickey is not a mouse", "Mouse", mickey.getCategory().getName());
		Assert.assertEquals("Mickey is missing tags", 2, mickey.getTags().size());
		Assert.assertNotNull("Category is missing", mickey.getCategory());
		Assert.assertEquals("Mickey is not a mouse","Mouse", mickey.getCategory().getName());
		
		
	}
	
	@Test
	public void testDelete() {
		petRepository.delete(1);
		Assert.assertFalse("Mickey is still there", petRepository.exists(1));
		
	}
	
	@Test
	public void testSave() {
		Pet pet = new PetBuilder().createPet();
		petRepository.save(pet);
		Pet createdPet = petRepository.findByName("Pat Hibulaire");
		Assert.assertNotNull("Entity not created", createdPet);
		Assert.assertNotNull("Tags are not well recorded", createdPet.getTags());
		Assert.assertEquals("Tag size is not right",2 , createdPet.getTags().size());
		Assert.assertNotNull("Photos are not well recorded", createdPet.getPhotoUrls());
		Assert.assertEquals("Photos size is not right",2 , createdPet.getPhotoUrls().size());
		
	}


	
}
