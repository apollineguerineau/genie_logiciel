package fr.ensai.demo.repository;

import fr.ensai.demo.model.scan.Scan2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanRepository extends CrudRepository<Scan2, Long> {

}
