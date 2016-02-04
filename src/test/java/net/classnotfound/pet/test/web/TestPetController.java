package net.classnotfound.pet.test.web;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.classnotfound.pet.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class TestPetController {
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testFind() throws Exception {
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/pet/1"));
		result.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Mickey")));
	}
	
	@Test
	public void testSave() throws Exception {
		MediaType contentType = MediaType.APPLICATION_JSON_UTF8;
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.post("/pet/")
				.contentType(contentType )
				.content(getJson()));
		result.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Daisy")));
	}

	private byte[] getJson() {
		String daisy = "{\n" + 
				"  \"category\": {\n" + 
				"    \"id\": 4,\n" + 
				"	\"name\": \"Duck\"\n" + 
				"  },\n" + 
				"  \"name\": \"Daisy\",\n" + 
				"  \"photoUrls\": [\n" + 
				"    \"http://cdn.s7.disneystore.com/is/image/DisneyShopping/1263000441835?$yetidetail$\",\n" + 
				"	\"http://ecx.images-amazon.com/images/I/31wb7vgfbGL._SY300_.jpg\"\n" + 
				"  ],\n" + 
				"  \"tags\": [\n" + 
				"    {\n" + 
				"      \"id\": 1,\n" + 
				"      \"name\": \"Walt Disney\"\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"status\": \"available\"\n" + 
				"}";
		return daisy.getBytes();
	}
	

}
