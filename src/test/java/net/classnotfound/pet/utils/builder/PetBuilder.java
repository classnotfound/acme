package net.classnotfound.pet.utils.builder;

import java.util.HashSet;
import java.util.Set;

import net.classnotfound.pet.domain.Category;
import net.classnotfound.pet.domain.Pet;
import net.classnotfound.pet.domain.Photo;
import net.classnotfound.pet.domain.Tag;

public class PetBuilder {
	
	public Pet createPet() {
		Pet pet = new Pet();
		pet.setName("Pat Hibulaire");
		pet.setTags(createTags(pet));
		Category category = new Category(2, null);
		pet.setCategory(category);
		Set<Photo> photoUrls = createPhotoUrls(pet);
		pet.setPhotoUrls(photoUrls );
		return pet;
	}

	private Set<Photo> createPhotoUrls(Pet pet) {
		
		Set<Photo> photos = new HashSet<>();
		Photo photo = new Photo(null, "http://static.comicvine.com/uploads/original/0/77/907356-gambadilegno.gif", pet);
		photos.add(photo);
		photo = new Photo(null, "http://wondersofdisney.yolasite.com/resources/pals/pete/petehotdog.png", pet);
		photos.add(photo);
		return photos ;
	}

	private Set<Tag> createTags(Pet pet) {
		Set<Tag> tags = new HashSet<>();
		Tag tag = new Tag(1, null, pet);
		tags.add(tag);
		tag = new Tag(4, null, pet);
		tags.add(tag);
		return tags;
	}
	

}
