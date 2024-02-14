package fr.ensai.demo.repository;

import fr.ensai.demo.model.filesystem.FileLeaf;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileLeafRepository extends CrudRepository<FileLeaf, Long> {
    public Iterable<FileLeaf> findByScanId(Long scanId);
    public Optional<FileLeaf> findByNameAndParentFolderName(String name, String parentFolderName);
}