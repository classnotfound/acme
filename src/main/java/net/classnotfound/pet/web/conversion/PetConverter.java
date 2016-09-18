package net.classnotfound.pet.web.conversion;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.classnotfound.pet.domain.Pet;
import net.classnotfound.pet.domain.Photo;
import net.classnotfound.pet.domain.Status;
import net.classnotfound.pet.domain.Tag;
import net.classnotfound.pet.web.json.Category;
import net.classnotfound.pet.web.json.JsonPet;
import net.classnotfound.pet.web.json.TagsEntry;

public class PetConverter {
	
	public JsonPet fromEntity(Pet e) {
		JsonPet j = new JsonPet();
		j.setName(e.getName());
		j.setId(e.getId());
		j.setStatus(e.getStatus()!=null? e.getStatus().toString().toLowerCase(): null);
		//Category
		j.setCategory(buildCategory(e.getCategory()));
		//Photo
		j.setPhotoUrls(buildPhoto(e.getPhotoUrls()));
		//Tag
		j.setTags(buildTags(e.getTags()));
		return j;
	}
	
	private Set<TagsEntry> buildTags(Set<Tag> tags) {
		Set<TagsEntry> jTags = new HashSet<>();
		for (Tag tag : tags) {
			TagsEntry tagEntry = new TagsEntry();
			tagEntry.setId(tag.getId());
			tagEntry.setName(tag.getName());
			jTags.add(tagEntry);
		}
		return jTags ;
	}

	private Set<String> buildPhoto(Set<Photo> photoUrls) {
		HashSet<String> jPhoto = new HashSet<>();
		for (Photo photo : photoUrls) {
			jPhoto.add(photo.getUrl());
		}
		return jPhoto;
	}

	private Category buildCategory(net.classnotfound.pet.domain.Category category) {
		Category jCategory = new Category();
		jCategory.setId(category.getId());
		jCategory.setName(category.getName());
		return jCategory;
	}

	public Pet fromJson(JsonPet json) {
		Pet pet = new Pet();
		pet.setName(json.getName());
		pet.setId(json.getId());
		pet.setStatus(json.getStatus()!=null? Status.valueOf(json.getStatus().toUpperCase()):null);
		//category
		pet.setCategory(buildCategory(json.getCategory()));
		//photo
		pet.setPhotoUrls(buildPhoto(json.getPhotoUrls(), pet));
		//tag
		pet.setTags(buildTags(json.getTags(), pet));
		return pet;
	}

	private Set<Tag> buildTags(Set<TagsEntry> tags, Pet pet) {
		if (tags==null||tags.isEmpty())
				return null;;
		Set<Tag> tagSet = new HashSet<>();
		for (TagsEntry tagsEntry : tags) {
			Tag tag = new Tag();
			tag.setId(tagsEntry.getId());
			tag.setName(tagsEntry.getName());
			Set<Pet> petSet = new HashSet<>();
			petSet.add(pet);
			tag.setPets(petSet);
			tagSet.add(tag);
		}
		return tagSet;
	}

	private Set<Photo> buildPhoto(Set<String> photoUrls, Pet pet) {
		if(photoUrls==null||photoUrls.isEmpty())
			return null;
		Set<Photo> photoSet = new HashSet<>();
		for (String string : photoUrls) {
			Photo photo = new Photo();
			photo.setUrl(string);
			photo.setPet(pet);
			photoSet.add(photo);
		}
		return photoSet;
	}

	private net.classnotfound.pet.domain.Category buildCategory(Category category) {
		if (category==null)
			return null;
		net.classnotfound.pet.domain.Category c = new net.classnotfound.pet.domain.Category();
		c.setId(category.getId());
		c.setName(category.getName());
		return c;
	}

	public Collection<JsonPet> fromEntityList(Collection<Pet> entity) {
		
		Collection<JsonPet> jsonPets = new ArrayList<>();
		for (Pet pet : entity) {
			jsonPets.add(fromEntity(pet));
		}
		return jsonPets;
	}
	
	

}
