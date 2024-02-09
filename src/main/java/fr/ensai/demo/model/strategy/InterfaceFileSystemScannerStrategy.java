package fr.ensai.demo.model.strategy;

import fr.ensai.demo.model.scan.Scan;
import fr.ensai.demo.model.filesystem.*;

public interface InterfaceFileSystemScannerStrategy {
    FolderComponent importFileSystem(String path, int currentDepth);
    Scan scanFileSystem(FolderComponent folder, String filenameFilter, String extensionFilter, int maxFiles, int maxDepth);
}
