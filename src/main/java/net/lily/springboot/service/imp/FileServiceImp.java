package net.lily.springboot.service.imp;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import net.lily.springboot.dao.FileMetadataDao;
import net.lily.springboot.dao.FileRepo;
import net.lily.springboot.dto.FileMetadataDto;
import net.lily.springboot.entity.FileMetadata;
import net.lily.springboot.service.FileService;

@Service
public class FileServiceImp implements FileService {
	
	private static final Logger LOG = LoggerFactory.getLogger(FileServiceImp.class);
	
	@Autowired
	FileMetadataDao metadataDao;
	@Autowired
	FileRepo fileRepo;

	@Override
	@Transactional
	public void addFileMetadata(FileMetadataDto dto, MultipartFile file) {
		String fileId = fileRepo.saveFile(file);
		LOG.info(file.getOriginalFilename() + " is uploaded as " + fileId);
		
		FileMetadata metadata =  new FileMetadata();
		metadata.setFileId(fileId);
		metadata.setFileName(file.getOriginalFilename());
		metadata.setOwner(dto.getOwner());
		metadata.setSize(file.getSize());
		metadata.setComment(dto.getComment());
		metadata.setUploadTime(new Date(System.currentTimeMillis()));
		
		metadataDao.addFileMetadata(metadata);
		LOG.info(metadata + " is saved.");
	}

	@Override
	@Transactional
	public FileMetadataDto getFileMetadataById(Long id) {
		FileMetadata temp = metadataDao.getFileMetadataById(id);
		if ( temp == null) {
			LOG.info("Metadata(id: " + id + ") is not found");
			return null;
		}
		LOG.info("Metadata(id: " + id + ") is found");
		return new FileMetadataDto(temp);
	}

	
	@Override
	@Transactional
	public List<FileMetadataDto> getFileMetadata() {
		List<FileMetadata> metadatas = metadataDao.getFileMetadata();
		List<FileMetadataDto> dtos = new ArrayList<FileMetadataDto>();
		for (FileMetadata metadata : metadatas) {
			dtos.add(new FileMetadataDto(metadata));
		}
		//throw new RuntimeException("Method not supported");
		return dtos;
	}

	@Override
	public byte[] getFileById(Long id) {
		FileMetadata metadata = metadataDao.getFileMetadataById(id);
		if (metadata == null) {
			LOG.info("Metadata(id: " + id + ") is not found");
			return null;
		}
		LOG.info("Metadata(id: " + id + ") is found");
		return fileRepo.getFile(metadata.getFileId());
	}
	
	@Override
	public List<FileMetadataDto> getFileMetadataByCondition(FileMetadataDto dto) {
		List<FileMetadata> metadatas = metadataDao.getFileMetadataByCondition(new FileMetadata(dto));
		List<FileMetadataDto> dtos = new ArrayList<FileMetadataDto>();
		
		for (FileMetadata metadata : metadatas) {
			dtos.add(new FileMetadataDto(metadata));
		}
		return dtos;
	}

}
