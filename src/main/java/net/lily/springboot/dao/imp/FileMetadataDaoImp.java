package net.lily.springboot.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.lily.springboot.dao.FileMetadataDao;
import net.lily.springboot.entity.FileMetadata;

@Repository
public class FileMetadataDaoImp implements FileMetadataDao {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public void addFileMetadata(FileMetadata file) {
		em.persist(file);
	}

	@Override
	public FileMetadata getFileMetadataById(Long id) {
		return em.find(FileMetadata.class, id);
	}

	@Override
	public List<FileMetadata> getFileMetadata() {
		String hql = "SELECT f FROM FileMetadata f";
		return em.createQuery(hql, FileMetadata.class).getResultList();
	}

	@Override
	public List<FileMetadata> getFileMetadataByUserId(String userId) {
		String hql = "SELECT f FROM FileMetadata f WHERE f.userId =: userId";
		return em.createQuery(hql, FileMetadata.class).
				setParameter("userId", userId).getResultList();
	}

	@Override
	public List<FileMetadata> getFileMetadataByCondition(FileMetadata metadata) {
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<FileMetadata> criteriaQuery = criteriaBuilder.createQuery(FileMetadata.class);
		Root<FileMetadata> root = criteriaQuery.from(FileMetadata.class);
		
		if (metadata.getFileName() != null) {
			conditions.add(criteriaBuilder.like(root.get("fileName").as(String.class), "%" + metadata.getFileName() + "%"));
		}
		
		if (metadata.getComment() != null) {
			conditions.add(criteriaBuilder.like(root.get("comment").as(String.class), "%" + metadata.getComment() + "%"));
		}
		
		if (metadata.getOwner() != null) {
			conditions.add(criteriaBuilder.equal(root.get("owner").as(String.class), metadata.getOwner()));
		}
		
		Predicate[] predicates = new Predicate[conditions.size()];
		criteriaQuery.where(conditions.toArray(predicates));
		
		return em.createQuery(criteriaQuery).getResultList();
	}

	
}
