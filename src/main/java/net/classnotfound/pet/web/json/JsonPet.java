package net.classnotfound.pet.web.json;

import java.util.Set;

import javax.validation.constraints.NotNull;

public class JsonPet {
	
	@NotNull
	private Set<String> photoUrls;
	
	@NotNull
	private String name;
	
	private Integer id;
	
	private Category category;
	
	private Set<TagsEntry> tags;
	
	private String status;

	
	public Set<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(Set<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<TagsEntry> getTags() {
		return tags;
	}

	public void setTags(Set<TagsEntry> tags) {
		this.tags = tags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
