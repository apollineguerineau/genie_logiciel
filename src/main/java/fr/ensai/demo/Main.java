// Client code to test the implementation
package fr.ensai.demo;

import fr.ensai.demo.model.filesystem.FileLeaf; // Import the class from the other package
import fr.ensai.demo.model.filesystem.FolderComponent; // Import the class from the other package
import fr.ensai.demo.model.visitor.FileSystemVisitor; // Import the class from the other package
import fr.ensai.demo.model.strategy.LocalFileSystemScanner;
// import fr.ensai.demo.model.strategy.S3FileSystemScanner;
// import fr.ensai.demo.model.scan.Scan;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // Creating the root directory
        LocalFileSystemScanner localFileSystemScanner = new LocalFileSystemScanner();
        FolderComponent rootDirectory = localFileSystemScanner.importFileSystem("/home/ensai/Documents/3A/GENIE_LOGICIEL/all/dirtest", 0);
        
        // Visiting the directory to get the list of files
        FileSystemVisitor visitor = new FileSystemVisitor("1", ".txt", 3, 100);
        rootDirectory.accept(visitor);

        // Getting the list of files
        List<FileLeaf> visitedFiles = visitor.getVisitedFiles();
        System.out.println("List of files:");
        for (FileLeaf file : visitedFiles) {
            System.out.println(file.getName());
            System.out.println(file.getFileExtension());
            System.out.println(file.getParentFolderName());
            System.out.println(file.getModificationDate());
            System.out.println(file.getSize());

        }
    
}
}


        // S3FileSystemScanner s3FileSystemScanner = new S3FileSystemScanner() ;
        // Scan scan = s3FileSystemScanner.scanFileSystem(null, null, null, 0, 0);
        // System.out.println("List of files:");
    //     for (FileLeaf file : scan.getScannedFiles()) {
    //         System.out.println(file.getName());
    //         System.out.println(file.getFileExtension());
    //         System.out.println(file.getParentFolderName());
    //         System.out.println(file.getModificationDate());
    //         System.out.println(file.getSize());

    // }