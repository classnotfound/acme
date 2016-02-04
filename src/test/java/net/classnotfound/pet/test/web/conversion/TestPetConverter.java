package net.classnotfound.pet.test.web.conversion;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.classnotfound.pet.Application;
import net.classnotfound.pet.domain.Pet;
import net.classnotfound.pet.domain.Photo;
import net.classnotfound.pet.service.PetService;
import net.classnotfound.pet.web.consersion.PetConverter;
import net.classnotfound.pet.web.json.Category;
import net.classnotfound.pet.web.json.JsonPet;
import net.classnotfound.pet.web.json.TagsEntry;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Transactional
public class TestPetConverter {
	
	@Autowired
	PetService petService;
	
	
	@Test
	public void testFromEntity() {
		Pet entity = petService.findByName("Minnie");
		Assert.assertNotNull("Entity not found in the database!!!", entity);
		PetConverter petConverter = new PetConverter();
		JsonPet jsonObject = petConverter.fromEntity(entity);
		Assert.assertNotNull("Converted object was null", jsonObject);
		Assert.assertEquals("Number of photo is wrong", 3, jsonObject.getPhotoUrls().size());
		Assert.assertEquals("Number of tags is wrong", 2, jsonObject.getTags().size());
		Assert.assertNotNull("Minnie shoud have a category", jsonObject.getCategory());
		Assert.assertEquals("Minnie should be a mouse","Mouse", jsonObject.getCategory().getName());
		
	}
	
	@Test
	public void testFromJson() {
		JsonPet json = buildJson();
		PetConverter petConverter = new PetConverter();
		Pet pet = petConverter.fromJson(json);
		Assert.assertNotNull("Converted object was null", pet);
		Assert.assertEquals("Number of photo is wrong", 3, pet.getPhotoUrls().size());
		Assert.assertEquals("Photo class is wrong", Photo.class, pet.getPhotoUrls().toArray()[0].getClass());
		Assert.assertEquals("Number of tags is wrong", 2, pet.getTags().size());
		
	}

	private JsonPet buildJson() {
		JsonPet j = new JsonPet();
		j.setName("Test");
		j.setStatus("available");
		Category category = new Category();
		category.setId(1);
		j.setCategory(category);
		Set<String> photoUrls = buildPhotoUrls();
		j.setPhotoUrls(photoUrls );
		
		Set<TagsEntry> tags = buildTags();
		j.setTags(tags );
		return j;
	}

	private Set<TagsEntry> buildTags() {
		HashSet<TagsEntry> set = new HashSet<>();
		TagsEntry te = new TagsEntry();
		te.setId(1);
		set.add(te);
		te = new TagsEntry();
		te.setId(2);
		set.add(te);
		return set;
	}

	private Set<String> buildPhotoUrls() {
		HashSet<String> set = new HashSet<>();
		set.add("photo1");
		set.add("photo2");
		set.add("photo3");
		return set;
	}

}
