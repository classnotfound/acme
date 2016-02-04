package net.classnotfound.sandbox;

import java.util.Arrays;

public class TestDozer {
	
//	public static void main(String[] args) {
//		Mapper mapper = new DozerBeanMapper();
//		
//		Src src = buildSrcObject();
//		Dst destObject =  
//		    mapper.map(src, Dst.class);
//		System.out.println("---------------------------------------------");
//		System.out.println(destObject.getName());
//		System.out.println(destObject.getStrings());
//		System.out.println(destObject.getItems());
//		System.out.println(destObject.getItems().get(0).getClass());
//		System.out.println(destObject.getItems().get(0).getId());
//		
//	}

	private static Src buildSrcObject() {
		Src src = new Src("test");
		src.setStrings(Arrays.asList(new String[] {"1","2","3"}));
		src.setItems(Arrays.asList(new String[] {"item1","item2","item3"}));
		
		return src;
	}

}




