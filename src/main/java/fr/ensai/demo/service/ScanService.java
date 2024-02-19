package fr.ensai.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

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
      Optional<FileLeaf> existingFile = fileLeafRepository.findByAttributes(file.getName(), file.getParentFolderName(), file.getModificationDate(), file.getSize());

        if (existingFile.isPresent()) {
            // Le fichier existe déjà, nous pouvons le récupérer
            FileLeaf existingFileEntity = existingFile.get();
            scan.setOneScannedFile(existingFileEntity, file);

        }
      }

    Scan savedScan = scanRepository.save(scan);
    return savedScan;
  }


  public Optional<Scan> updateScan(Long id, Scan updatedScan) {
    // Vérifier si le scan existe
    Optional<Scan> optionalScan = scanRepository.findById(id);
    if (optionalScan.isPresent()) {
        // Récupérer le scan existant
        Scan existingScan = optionalScan.get();
        // Mettre à jour les propriétés du scan existant avec les nouvelles valeurs
        existingScan.setFileSystemType(updatedScan.getFileSystemType());
        existingScan.setScanDate(updatedScan.getScanDate());
        // Mettre à jour d'autres propriétés selon vos besoins

        // Enregistrer le scan mis à jour dans la base de données
        Scan updatedScanEntity = scanRepository.save(existingScan);
        return Optional.of(updatedScanEntity);
    } else {
        // Le scan avec l'ID donné n'existe pas
        return Optional.empty();
    }
  }

  public long countScans() {
    return scanRepository.count();
  }

  // Méthode pour comparer deux scans et retourner les différences
  public String compareScans(long id1, long id2) {
    Optional<Scan> scan1Op =  scanRepository.findById(id1);
    if (!scan1Op.isPresent()) {
      String result = "Le scan d'id " + id1 + "n'est pas présent dans la bdd";
      return(result);
    }
    Optional<Scan> scan2Op =  scanRepository.findById(id2);
    if (!scan2Op.isPresent()) {
      String result_ = "Le scan d'id " + id1 + "n'est pas présent dans la bdd";
      return(result_);
    }
    Scan scan1 = scan1Op.get();
    Scan scan2 = scan2Op.get();

    // Collecter les différences
    StringJoiner differences = new StringJoiner("\n");

    // Comparer les attributs des scans
    if (!scan1.getFileSystemType().equals(scan2.getFileSystemType())) {
      differences.add("FileSystemType: " + scan1.getFileSystemType() + " vs " + scan2.getFileSystemType());
    }
    if (!scan1.getScanDate().equals(scan2.getScanDate())) {
      differences.add("ScanDate: " + scan1.getScanDate() + " vs " + scan2.getScanDate());
    }
    if (scan1.getFileNameFilter() != null){
      if (scan2.getFileNameFilter() != null){
        if (!scan1.getFileNameFilter().equals(scan2.getFileNameFilter())) {
          differences.add("FileNameFilter: " + scan1.getFileNameFilter() + " vs " + scan2.getFileNameFilter());
      }
      }else{
        differences.add("FileNameFilter: " + scan1.getFileNameFilter() + " vs null");
      }
    }else{
      if(scan2.getFileNameFilter() != null){
        differences.add("FileNameFilter: null vs " + scan2.getFileNameFilter());
      }
    }

    if (scan1.getExtensionFilter() != null){
      if (scan2.getExtensionFilter() != null){
        if (!scan1.getExtensionFilter().equals(scan2.getExtensionFilter())) {
          differences.add("ExtensionFilter: " + scan1.getExtensionFilter() + " vs " + scan2.getExtensionFilter());
      }
      }else{
        differences.add("ExtensionFilter: " + scan1.getExtensionFilter() + " vs null");
      }
    }else{
      if(scan2.getExtensionFilter() != null){
        differences.add("ExtensionFilter: null vs " + scan2.getExtensionFilter());
      }
    }
  
    if (scan1.getExecutionTime()!=scan2.getExecutionTime()) {
      differences.add("ExecutionTime: " + scan1.getExecutionTime() + " vs " + scan2.getExecutionTime());
    }
    if (scan1.getSize()!=scan2.getSize()) {
      differences.add("Size: " + scan1.getSize() + " vs " + scan2.getSize());
    }
    if (!scan1.getRootDirectoryName().equals(scan2.getRootDirectoryName())) {
      differences.add("RootDirectoryName: " + scan1.getRootDirectoryName() + " vs " + scan2.getRootDirectoryName());
    }
    if (scan1.getMaxFiles()!=scan2.getMaxFiles()) {
      differences.add("MaxFiles: " + scan1.getMaxFiles() + " vs " + scan2.getMaxFiles());
    }
    if (scan1.getMaxDepth()!=scan2.getMaxDepth()) {
      differences.add("MaxDepth: " + scan1.getMaxDepth() + " vs " + scan2.getMaxDepth());
    }

    // Comparer les fichiers scannés
    List<FileLeaf> scannedFiles1 = scan1.getScannedFiles();
    List<FileLeaf> scannedFiles2 = scan2.getScannedFiles();

    List<Long> fileIds1 = new ArrayList<>();
    for (FileLeaf file : scannedFiles1) {
        fileIds1.add(file.getId());
    }

    List<Long> fileIds2 = new ArrayList<>();
    for (FileLeaf file : scannedFiles2) {
        fileIds2.add(file.getId());
    }

    List<Long> commonFileIds = new ArrayList<>(fileIds1);
    commonFileIds.retainAll(fileIds2);

    // IDs présents dans scannedFiles1 mais pas dans scannedFiles2
    List<Long> fileIdsOnlyInScannedFiles1 = new ArrayList<>(fileIds1);
    fileIdsOnlyInScannedFiles1.removeAll(fileIds2);

    // IDs présents dans scannedFiles2 mais pas dans scannedFiles1
    List<Long> fileIdsOnlyInScannedFiles2 = new ArrayList<>(fileIds2);
    fileIdsOnlyInScannedFiles2.removeAll(fileIds1);
    
    if(commonFileIds.size()!=fileIds1.size()){
      differences.add("--------------------------");
      differences.add("Fichiers communs : " + commonFileIds);
      differences.add("--------------------------");
      differences.add("Fichiers uniquement dans le scan " + id1 + " : " + fileIdsOnlyInScannedFiles1);
      differences.add("--------------------------");
      differences.add("Fichiers uniquement dans le scan " + id2 + " : " + fileIdsOnlyInScannedFiles2);
    }

    // Retourner les différences sous forme de chaîne de caractères
    if (differences.length() == 0) {
        return "Les scans sont identiques.";
    } else {
        return "Différences détectées:" + "\n" + differences.toString();
    }
  }
}