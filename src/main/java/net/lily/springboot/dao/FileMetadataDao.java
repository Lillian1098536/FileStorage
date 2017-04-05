package net.lily.springboot.dao;

import java.util.List;

import net.lily.springboot.entity.FileMetadata;

public interface FileMetadataDao {
	public void addFileMetadata(FileMetadata file);
	public FileMetadata getFileMetadataById(Long id);
	public List<FileMetadata> getFileMetadata();
	public List<FileMetadata> getFileMetadataByUserId(String userId);
	public List<FileMetadata> getFileMetadataByCondition(FileMetadata metadata);
}
