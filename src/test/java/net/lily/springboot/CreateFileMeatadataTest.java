package net.lily.springboot;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import net.lily.springboot.dto.FileMetadataDto;

//no POST method 

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateFileMeatadataTest {
	
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void createFileMetadata() {
    	FileMetadataDto metadataDto = new FileMetadataDto();
    	metadataDto.setFileName("tst.txt");
    	
        ResponseEntity<FileMetadataDto> responseEntity =
            restTemplate.postForEntity("/file_storage/metadata", metadataDto, FileMetadataDto.class);
        FileMetadataDto dto = responseEntity.getBody();
        
        System.err.println(responseEntity.getStatusCode());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        System.err.println(dto.getFileName());
        assertEquals("tst.txt", dto.getFileName());
    }
}


//11l, "test.txt", "tester", 4l, "2017-03-27 14:07:01.436", "uploaded 1 file"