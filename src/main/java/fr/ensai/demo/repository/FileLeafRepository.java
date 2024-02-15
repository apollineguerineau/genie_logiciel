package fr.ensai.demo.repository;

import fr.ensai.demo.model.filesystem.FileLeaf;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FileLeafRepository extends CrudRepository<FileLeaf, Long> {
    public Iterable<FileLeaf> findByScanId(Long scanId);
    public Optional<FileLeaf> findById(Long id);
    
    @Query("SELECT f FROM FileLeaf f WHERE f.name = :name AND f.parentFolderName = :parentFolderName AND f.modificationDate = :modificationDate AND f.size = :size")
    Optional<FileLeaf> findByAttributes(@Param("name") String name, @Param("parentFolderName") String parentFolderName, @Param("modificationDate") String modificationDate, @Param("size") long size);
}