package fr.ensai.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.filesystem.FileLeaf2;
import fr.ensai.demo.model.filesystem.FolderComponent;
import fr.ensai.demo.model.scan.Scan;
import fr.ensai.demo.model.scan.Scan2;
import fr.ensai.demo.model.strategy.LocalFileSystemScanner;
import fr.ensai.demo.model.strategy.S3FileSystemScanner;
import fr.ensai.demo.service.ScanService;
import fr.ensai.demo.repository.ScanRepository;
import fr.ensai.demo.service.FileLeafService;
import fr.ensai.demo.repository.FileLeafRepository;
// import fr.ensai.demo.model.visitor.FileSystemVisitor;
// import software.amazon.awssdk.regions.Region;
// import software.amazon.awssdk.services.s3.S3Client;
// import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
// import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
// import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;
import java.util.Optional;

@RestController
public class HelloWorldController {
    @Autowired
    private ScanService scanService;

    @Autowired
    private FileLeafService fileLeafService;


    // private final ScanService scanService;

    // @Autowired
    // public HelloWorldController(ScanService scanService) {
    //     this.scanService = scanService;
    // }

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }

    @GetMapping("/local_file_system")
        public ResponseEntity<Scan> getLocalFileSystemInfo(@RequestParam String path,
                                           @RequestParam(required = false) String filenameFilter,
                                           @RequestParam(required = false) String extensionFilter) {
            // Creating the root directory
            LocalFileSystemScanner localFileSystemScanner = new LocalFileSystemScanner();
            FolderComponent rootDirectory = localFileSystemScanner.importFileSystem(path, 0);

            // FolderComponent rootDirectory = localFileSystemScanner.importFileSystem("/home/ensai/Documents/3A/GENIE_LOGICIEL/all/dirtest", 0);
            Scan scan = localFileSystemScanner.scanFileSystem(rootDirectory,filenameFilter, extensionFilter, 10, 10);
            // scanService.saveOrUpdateScan(scan);

            return new ResponseEntity<>(scan, HttpStatus.OK);

    }

    // @GetMapping("/scan")
    
    //     public String newScan() {
    //         // Création d'un objet Scan
    //         Scan2 scan = new Scan2("local", "2024-02-15", null, null, "/path_test", 0, 0, 10, 10);

    //         // Création des objets FileLeaf pour chaque fichier
    //         FileLeaf2 file1 = new FileLeaf2("file1.txt", "/path_test", "txt", "15/02/2024", 100, 1);
    //         FileLeaf2 file2 = new FileLeaf2("file2.txt", "/path_test", "txt", "15/02/2024", 200, 1);

    //         // Ajout des fichiers à la liste des fichiers scannés de l'objet Scan
    //         scan.addScannedFile(file1);
    //         scan.addScannedFile(file2);
            
    //         scanService.saveScan(scan);
    //         return("test");
    //     }

    // @GetMapping("/scans")
    //     public ResponseEntity<Iterable<Scan2>> getAllScans() {
    //         Iterable<Scan2> scans = scanService.getScans();
    //         // Pour chaque scan, charger les fichiers associés
    //         for (Scan2 scan : scans) {
    //             List<FileLeaf2> scannedFiles = fileLeafService.getFilesByScanId(scan.getId());
    //             // System.out.println(scannedFiles);
    //             scan.setScannedFiles(scannedFiles);
    // }
    //         return new ResponseEntity<>(scans, HttpStatus.OK);
    //     }
    
    // @GetMapping("/findscan")

    //     public ResponseEntity<Scan2> getScanById() {
    //         Optional<Scan2> scanOptional = scanService.getScan(1L);
    //         if (scanOptional.isPresent()) {
    //             Scan2 scan = scanOptional.get();
    //             List<FileLeaf2> scannedFiles = fileLeafService.getFilesByScanId(1L);
    //             System.out.println(scannedFiles);
    //             scan.setScannedFiles(scannedFiles);
    //             return new ResponseEntity<>(scan, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //         }
    //     }
        

    @GetMapping("/S3_file_system")
    public ResponseEntity<Scan> getS3FileSystem(@RequestParam String bucketName,
                                @RequestParam(required = false) String filenameFilter,
                                @RequestParam(required = false) String extensionFilter) {
        // String bucketName = "bucketprojectapolline";
        S3FileSystemScanner s3FileSystemScanner = new S3FileSystemScanner();
        FolderComponent rootDirectory = s3FileSystemScanner.importFileSystem(bucketName, 0);
        Scan scan = s3FileSystemScanner.scanFileSystem(rootDirectory,filenameFilter,extensionFilter, 10, 10);
        return new ResponseEntity<>(scan, HttpStatus.OK);

    }
}



