package net.lily.springboot.dto;

import java.io.Serializable;

import net.lily.springboot.entity.FileMetadata;

public class FileMetadataDto implements Serializable{

	private static final long serialVersionUID = -4351281772747041537L;

	private Long id;
	private String fileName;
	private String owner;
	private Long size;
	private String uploadTime;
	private String comment;
	
	public FileMetadataDto() { }
	
	public FileMetadataDto(FileMetadata metadata) {
		this.id = metadata.getId();
		this.fileName = metadata.getFileName();
		this.owner = metadata.getOwner();
		this.size = metadata.getSize();
		this.uploadTime = metadata.getUploadTime().toString();
		this.comment = metadata.getComment();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((uploadTime == null) ? 0 : uploadTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileMetadataDto other = (FileMetadataDto) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (uploadTime == null) {
			if (other.uploadTime != null)
				return false;
		} else if (!uploadTime.equals(other.uploadTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileMetadataDto [id=" + id + ", fileName=" + fileName + ", owner=" + owner + ", size=" + size
				+ ", uploadTime=" + uploadTime + ", comment=" + comment + "]";
	}
	
}
