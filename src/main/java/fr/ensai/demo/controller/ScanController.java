package fr.ensai.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.filesystem.FolderComponent;
import fr.ensai.demo.model.scan.Scan;
import fr.ensai.demo.model.scan.Scan;
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
public class ScanController {
    @Autowired
    private ScanService scanService;

    @Autowired
    private FileLeafService fileLeafService;

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("hello world", HttpStatus.OK);
    }

    @GetMapping("/create_scan/local_file_system")
        public ResponseEntity<Scan> getLocalFileSystemInfo(@RequestParam String path,
                                           @RequestParam(required = false) String filenameFilter,
                                           @RequestParam(required = false) String extensionFilter) {
            // Creating the root directory
            LocalFileSystemScanner localFileSystemScanner = new LocalFileSystemScanner();
            FolderComponent rootDirectory = localFileSystemScanner.importFileSystem(path, 0);
            Scan scan = localFileSystemScanner.scanFileSystem(rootDirectory,filenameFilter, extensionFilter, 10, 10);
            scanService.saveScan(scan);

            return new ResponseEntity<>(scan, HttpStatus.OK);

    }

    @GetMapping("/create_scan/S3_file_system")
    public ResponseEntity<Scan> getS3FileSystem(@RequestParam String bucketName,
                                @RequestParam(required = false) String filenameFilter,
                                @RequestParam(required = false) String extensionFilter) {
        // String bucketName = "bucketprojectapolline";
        S3FileSystemScanner s3FileSystemScanner = new S3FileSystemScanner();
        FolderComponent rootDirectory = s3FileSystemScanner.importFileSystem(bucketName, 0);
        Scan scan = s3FileSystemScanner.scanFileSystem(rootDirectory,filenameFilter,extensionFilter, 10, 10);
        scanService.saveScan(scan);
        return new ResponseEntity<>(scan, HttpStatus.OK);

    }

    @GetMapping("/get_all_scans")
        public ResponseEntity<Iterable<Scan>> getAllScans() {
            Iterable<Scan> scans = scanService.getScans();
    
            return new ResponseEntity<>(scans, HttpStatus.OK);
        }
    

    @GetMapping("/get_scan")
        public ResponseEntity<Optional<Scan>> getScanById(@RequestParam Long id) {
            Optional<Scan> scan = scanService.getScanById(id);
            return new ResponseEntity<>(scan, HttpStatus.OK);
        }

    @GetMapping("/delete_scan")
    public ResponseEntity<String> deleteScan(@RequestParam Long id) {
        boolean deleted = scanService.deleteScan(id);
        if (deleted) {
            return new ResponseEntity<>("Scan with ID " + id + " deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Scan with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/replay_scan")
    public ResponseEntity<String> updateScan(@RequestParam Long id) {
        Optional<Scan> scan = scanService.getScanById(id);
        if (scan.isPresent()) {
            String fileSystemType = scan.get().getFileSystemType();
            String scanDate = scan.get().getScanDate();
            String fileNameFilter = scan.get().getFileNameFilter();
            String extensionFilter = scan.get().getExtensionFilter();
            String rootDirectoryName = scan.get().getRootDirectoryName();
            float executionTime = scan.get().getExecutionTime();
            int size = scan.get().getSize();
            int maxFiles = scan.get().getMaxFiles();
            int maxDepth = scan.get().getMaxDepth();
            if (fileSystemType.equals("local file system")){
                LocalFileSystemScanner localFileSystemScanner = new LocalFileSystemScanner();
                FolderComponent rootDirectory = localFileSystemScanner.importFileSystem(rootDirectoryName, 0);
                Scan newScan = localFileSystemScanner.scanFileSystem(rootDirectory,fileNameFilter, extensionFilter, maxFiles, maxDepth);
                scanService.updateScan(id, newScan);
                
            }else{
                S3FileSystemScanner s3FileSystemScanner = new S3FileSystemScanner();
                FolderComponent rootDirectory = s3FileSystemScanner.importFileSystem(rootDirectoryName, 0);
                Scan newScan = s3FileSystemScanner.scanFileSystem(rootDirectory,fileNameFilter, extensionFilter, maxFiles, maxDepth);
                scanService.updateScan(id, newScan);
            }   
            return new ResponseEntity<>("okay", HttpStatus.OK); 
        }
        return new ResponseEntity<>("nop", HttpStatus.OK); 
    }

    @GetMapping("/compare_scans")
    public ResponseEntity<String> compareScans(@RequestParam Long id1, 
                                               @RequestParam Long id2) {
        String differences = scanService.compareScans(id1,id2);
        // Générer une réponse HTML
        String htmlResponse = "<html><body><h1>Différences détectées :</h1><pre>" + differences + "</pre></body></html>";
        return new ResponseEntity<>(htmlResponse, HttpStatus.OK);
    }


}



