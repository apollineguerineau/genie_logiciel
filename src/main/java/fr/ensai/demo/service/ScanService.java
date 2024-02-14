package fr.ensai.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.scan.Scan;
import fr.ensai.demo.repository.ScanRepository;
import fr.ensai.demo.repository.FileLeafRepository;

@Service
public class ScanService {

  Logger LOG  = LoggerFactory.getLogger(ScanService.class);

  @Autowired
  private ScanRepository scanRepository;

  @Autowired
  private FileLeafRepository fileLeafRepository;
 
  public Optional<Scan> getScanById(final Long id) {
    Optional<Scan> scan =  scanRepository.findById(id);
    return(scan);
  }

  public Iterable<Scan> getScans() {
    return scanRepository.findAll();
  }

  public boolean deleteScan(final Long id) {
    Optional<Scan> scan = getScanById(id);
    scanRepository.deleteById(id);
    return scan.isPresent();
  }

  public Scan saveScan(Scan scan) {
    Iterable<FileLeaf> scannedFiles = scan.getScannedFiles();

    List<FileLeaf> copyOfScannedFiles = new ArrayList<>();
    // Copie des éléments de la liste d'origine dans la nouvelle liste
    for (FileLeaf file : scannedFiles) {
        copyOfScannedFiles.add(file);
    }

    for (FileLeaf file : copyOfScannedFiles) {
      Optional<FileLeaf> existingFile = fileLeafRepository.findByNameAndParentFolderName(file.getName(), file.getParentFolderName());

        if (existingFile.isPresent()) {
            // Le fichier existe déjà, nous pouvons le récupérer
            FileLeaf existingFileEntity = existingFile.get();
            scan.setOneScannedFile(existingFileEntity, file);

        }
      }
    // // Créer un comparateur basé sur les identifiants des fichiers en ordre croissant
    // Comparator<FileLeaf> comparator = new Comparator<FileLeaf>() {
    //     @Override

    //     public int compare(FileLeaf file1, FileLeaf file2) {
    //         return Long.compare(file1.getId(), file2.getId());
    //     }
    // };
    // // Trier la liste scannedFiles en utilisant le comparateur
    // Collections.sort(scan.getScannedFiles(), comparator);

    Scan savedScan = scanRepository.save(scan);
    return savedScan;
  }

  public void deleteScanById(Long id) {
    scanRepository.deleteById(id);
  }

  public long countScans() {
    return scanRepository.count();
  }
}