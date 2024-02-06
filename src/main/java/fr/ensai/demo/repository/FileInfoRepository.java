package fr.ensai.demo.repository;

import fr.ensai.demo.model.FileInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends CrudRepository<FileInfo, Long> {
    public Iterable<ScanRepository> findByScanId(int scanId);
}