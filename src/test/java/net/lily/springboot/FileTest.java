package net.lily.springboot;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.Before;
import org.junit.Test;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import net.lily.springboot.dto.FileMetadataDto;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//@RestClientTest(FileService.class)
public class FileTest {

//	@Autowired
//	private RestTemplate template;
	
//	private FileMetadataDto dto = new FileMetadataDto();
	
	@Before
	public void setUp() throws Exception {
/*		FileMetadataDto dto = new FileMetadataDto();
		RestTemplate template = new RestTemplate();
		dto.setId(1l);
		dto.setOwner("tester");
		dto.setComment("uploaded 1 file");
		
		String url = "http://127.0.0.1:8080/file_storage/file";
		String filePath = "/Users/CS/Desktop/test/test.txt";
		
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("file", new FileSystemResource(new File(filePath)));	
		param.add("owner", dto.getOwner());
		param.add("comment", dto.getComment());
		
		template.postForObject(url, param, String.class);*/
	}
	
	@Test
	public void getFileMetadataTest() throws Exception {
		//assertEquals(1, 1);
		
		FileMetadataDto dto = new FileMetadataDto();
		RestTemplate template = new RestTemplate();
		dto.setId(1l);
		dto.setOwner("tester");
		dto.setComment("uploaded 1 file");
		
		String url = "http://127.0.0.1:8080/file_storage/file";
		String filePath = "/Users/CS/Desktop/test/test.txt";
		
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("file", new FileSystemResource(new File(filePath)));	
		param.add("owner", dto.getOwner());
		param.add("comment", dto.getComment());
		
		template.postForEntity(url, param, String.class);
		ResponseEntity<FileMetadataDto> response = template
				.getForEntity("http://127.0.0.1:8080/file_storage/metadata/1", 
						FileMetadataDto.class);
		
		//System.err.print("**************");
		
		assertEquals(dto.getOwner(), response.getBody().getOwner());
	}
}
