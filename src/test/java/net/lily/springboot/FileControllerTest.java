package net.lily.springboot;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import net.lily.springboot.service.FileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private FileService service;
	
	@Test
	public void testController() throws Exception {

		
		mvc.perform(MockMvcRequestBuilders.get("/file_storage/metadata?owner=tester").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("  ")));
	}
}
