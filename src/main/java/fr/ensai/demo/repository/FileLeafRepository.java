package fr.ensai.demo.repository;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.filesystem.FileLeaf2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileLeafRepository extends CrudRepository<FileLeaf2, Long> {
    List<FileLeaf2> findByScanId(Long scanId);

}