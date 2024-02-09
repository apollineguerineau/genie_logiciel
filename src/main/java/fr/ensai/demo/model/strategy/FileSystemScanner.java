
package fr.ensai.demo.model.strategy;
import fr.ensai.demo.model.filesystem.FolderComponent;
import fr.ensai.demo.model.scan.Scan;

public class FileSystemScanner {
    private InterfaceFileSystemScannerStrategy strategy;

    // Setter method for setting strategy
    public void setStrategy(InterfaceFileSystemScannerStrategy strategy) {
        this.strategy = strategy;
    }

    // Method to perform scanning
    public Scan scan(String pathToDir, String filenameFilter, String extensionFilter, int maxFiles, int maxDepth) {
        // Logic for scanning using strategy
        if (strategy != null) {
            FolderComponent folder = strategy.importFileSystem(pathToDir, 0);
            Scan scan = strategy.scanFileSystem(folder, 
                                       filenameFilter, 
                                       extensionFilter, 
                                       maxFiles, 
                                       maxDepth);
            return scan;

        } else {
            System.out.println("Strategy not set. Cannot perform scanning.");
            return null;
        }
    }
}

