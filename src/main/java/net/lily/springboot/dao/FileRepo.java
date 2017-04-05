package net.lily.springboot.dao;

import org.springframework.web.multipart.MultipartFile;

public interface FileRepo {
	public String saveFile(MultipartFile file);
	public byte[] getFile(String fileName);
}
