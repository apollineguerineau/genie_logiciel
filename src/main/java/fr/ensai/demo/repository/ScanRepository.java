package fr.ensai.demo.repository;

import fr.ensai.demo.model.scan.Scan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanRepository extends CrudRepository<Scan, Long> {

}
