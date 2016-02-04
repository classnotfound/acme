package net.classnotfound.sandbox;

import java.util.List;

public class Src {
	private String name;
	
	private List<String> strings;
	
	private List<String> items;
	
	public Src() {
		super();
	}
	
	public Src(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}
	
	
}