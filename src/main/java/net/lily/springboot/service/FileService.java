package net.lily.springboot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.lily.springboot.dto.FileMetadataDto;

public interface FileService {
	public void addFileMetadata(FileMetadataDto dto, MultipartFile file);
	public FileMetadataDto getFileMetadataById(Long id);
	public List<FileMetadataDto> getFileMetadataByCondition(FileMetadataDto dto);
	public List<FileMetadataDto> getFileMetadata();
	public byte[] getFileById(Long id);
}
