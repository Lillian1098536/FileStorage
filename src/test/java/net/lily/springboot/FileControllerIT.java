package net.lily.springboot;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import net.lily.springboot.dto.FileMetadataDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileControllerIT {

	@LocalServerPort
	private int port;
	
	private URL base;
	
	@Autowired
	private TestRestTemplate template;
	
	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/file_storage/metadata/1");
		FileMetadataDto dto = new FileMetadataDto();
		TestRestTemplate template = new TestRestTemplate();
		
		dto.setId(11l);
		dto.setOwner("tester");
		dto.setComment("uploaded the 11th file");
		
		String url = "http://127.0.0.1:" + port + "/file_storage/file";
		String filePath = "/Users/CS/Desktop/test/test.txt";
		
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("file", new FileSystemResource(new File(filePath)));	
		param.add("owner", dto.getOwner());
		param.add("comment", dto.getComment());
		
		template.postForEntity(url, param, String.class);
	}
	
	@Test
	public void testDtoFileName() throws Exception {
		ResponseEntity<FileMetadataDto> response = template.getForEntity(base.toString(), FileMetadataDto.class);	
//		System.err.println(base.toString());
//		System.err.println(response.getBody());
		
		FileMetadataDto dto = new FileMetadataDto();
		dto.setId(11l);
		dto.setFileName("test.txt");
		dto.setOwner("tester");
		dto.setSize(4l);
		dto.setUploadTime("2017-03-27 14:07:01.436");
		dto.setComment("uploaded the 11th file");	
		
//		System.out.println("response: " + response.getBody().getFileName());
//		System.out.println("dto: " + dto.getFileName());
        assertEquals(dto.getFileName(), response.getBody().getFileName());
	}
	
	@Test
	public void testDtoComment() throws Exception {
		ResponseEntity<FileMetadataDto> response = template.getForEntity(base.toString(), FileMetadataDto.class);	
		FileMetadataDto dto = new FileMetadataDto();
		dto.setId(11l);
		dto.setFileName("test.txt");
		dto.setOwner("tester");
		dto.setSize(4l);
		dto.setUploadTime("2017-03-27 14:07:01.436");
		dto.setComment("uploaded the 11th file");
		
		assertEquals(dto.getComment(), response.getBody().getComment());
	}

}