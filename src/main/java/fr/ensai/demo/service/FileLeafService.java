package fr.ensai.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.filesystem.FileLeaf2;
import fr.ensai.demo.model.scan.Scan2;
import fr.ensai.demo.repository.FileLeafRepository;
import fr.ensai.demo.repository.ScanRepository;

import java.util.List;



@Service
public class FileLeafService {

    Logger LOG  = LoggerFactory.getLogger(FileLeafService.class);

    @Autowired
    private FileLeafRepository fileLeafRepository;

    public List<FileLeaf2> getFilesByScanId(Long scanId) {
        return fileLeafRepository.findByScanId(scanId);
    }

    public FileLeaf2 saveFile(FileLeaf2 file) {
        FileLeaf2 savedFile = fileLeafRepository.save(file);

        return savedFile;
  }


    }