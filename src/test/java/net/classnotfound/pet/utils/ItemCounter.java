package net.classnotfound.pet.utils;

public class ItemCounter<T> {
	
	private Iterable<T> iter;

	public ItemCounter(Iterable<T> iter) {
		super();
		this.iter = iter;
	}

	public long size() {
		long size = 0;
		for (T t : iter) {
			size++;
		}
		return size;
	}
}
