package fr.ensai.demo.service;

import java.util.Locale.Category;
import java.util.Optional;

// import org.hibernate.mapping.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.repository.FileLeafRepository;


@Service
public class FileLeafService {

    Logger LOG  = LoggerFactory.getLogger(FileLeafService.class);

    @Autowired
    private FileLeafRepository fileLeafRepository;

    public Iterable<FileLeaf> getFiles() {
        return fileLeafRepository.findAll();
    }

    public Optional<FileLeaf> getFileById(Long id) {
		return fileLeafRepository.findById(id);
	}

    public boolean deleteScan(final Long id) {
        Optional<FileLeaf> file = getFileById(id);
        fileLeafRepository.deleteById(id);
        return file.isPresent();
      }
    
    public long countFiles() {
        return fileLeafRepository.count();
      }

    public Iterable<FileLeaf> getFilesByScanId(Long scanId) {
		  return fileLeafRepository.findByScanId(scanId);
	  }

    public void deleteFileById(Long id) {
      fileLeafRepository.deleteById(id);
    }
}