package net.lily.springboot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.lily.springboot.dto.FileMetadataDto;


@Entity
@Table(name="filemetadata")
public class FileMetadata implements Serializable {
 
	private static final long serialVersionUID = -6396430720455092004L;

	private Long id;
	private String fileName;
	private String owner;
	private Long size;
	private Date uploadTime;
	private String comment;
	private String fileId;
	
	public FileMetadata() { }
	
	public FileMetadata(FileMetadataDto dto) {
		this.fileName = dto.getFileName();
		this.owner = dto.getOwner();
		this.size = dto.getSize();
		this.uploadTime = new Date(System.currentTimeMillis());
		this.comment = dto.getComment();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(length = 128, nullable = false)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(length = 128, nullable = false)
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Column(length = 512, nullable = false)
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	@Column(nullable = false)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(nullable = false)
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
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
		FileMetadata other = (FileMetadata) obj;
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
		return "FileMetadata [id=" + id + ", fileName=" + fileName + ", owner=" + owner + ", size=" + size
				+ ", uploadTime=" + uploadTime + ", comment=" + comment + "]";
	}
 
}
