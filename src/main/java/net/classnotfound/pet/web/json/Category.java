package net.classnotfound.pet.web.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
	
	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private Integer id;

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
	
}
