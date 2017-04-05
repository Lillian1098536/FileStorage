package net.lily.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.lily.springboot.dto.FileMetadataDto;
import net.lily.springboot.service.FileService;

@RestController
@RequestMapping("/file_storage")
public class FileController {

	@Autowired
	FileService fileService;
	
	@RequestMapping(value = "/metadata/{id}", method = RequestMethod.GET)
	public ResponseEntity<FileMetadataDto> getFileMetadata(
			@PathVariable(value="id") Long id) {
		FileMetadataDto fileDto = fileService.getFileMetadataById(id);
		if (fileDto == null) {
			return new ResponseEntity<FileMetadataDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FileMetadataDto>(fileDto, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/metadata", method = RequestMethod.GET)
	public ResponseEntity<List<FileMetadataDto>> getFileMetadataByCondition(
			FileMetadataDto fileDto) {
		List<FileMetadataDto> dtos = fileService.getFileMetadataByCondition(fileDto);

		return new ResponseEntity<List<FileMetadataDto>>(dtos, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public ResponseEntity<FileMetadataDto> uploadFile(FileMetadataDto fileDto, MultipartFile file) {
		if (file.isEmpty()) {
			return new ResponseEntity<FileMetadataDto>(HttpStatus.NOT_FOUND);
		}
		fileService.addFileMetadata(fileDto, file);
		return new ResponseEntity<FileMetadataDto>(HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadFileById(@PathVariable(value="id") Long id) {
		FileMetadataDto fileDto = fileService.getFileMetadataById(id);
		byte[] file = fileService.getFileById(id);
		
		if (file == null || fileDto == null) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/octet-stream");
		headers.add("Content-Disposition", "attachment; filename = '" + fileDto.getFileName() + "'");
		
		return new ResponseEntity<byte[]>(file, headers, HttpStatus.FOUND);
	}
}
