package fr.ensai.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ensai.demo.model.filesystem.FileLeaf2;
import fr.ensai.demo.model.scan.Scan2;
import fr.ensai.demo.repository.ScanRepository;
import fr.ensai.demo.repository.FileLeafRepository;

@Service
public class ScanService {

  Logger LOG  = LoggerFactory.getLogger(ScanService.class);

  @Autowired
  private ScanRepository scanRepository;
  @Autowired
  private FileLeafService fileLeafService;

  public Optional<Scan2> getScan(final Long id) {
    Optional<Scan2> scan=  scanRepository.findById(id);
    return(scan);
  }

  public Iterable<Scan2> getScans() {
    return scanRepository.findAll();
  }

  public boolean deleteScan(final Long id) {
    Optional<Scan2> scan = getScan(id);
    scanRepository.deleteById(id);
    return scan.isPresent();
  }

  public Scan2 saveScan(Scan2 scan) {
    Scan2 savedScan = scanRepository.save(scan);
    for (FileLeaf2 file : scan.getScannedFiles()) {
            // Associer chaque fichier au scan
            // file.setScan(scan);
            // Enregistrer le fichier dans la base de données
            fileLeafService.saveFile(file);
        }
    return savedScan;
  }

  public long countScans() {
    return scanRepository.count();
  }
}