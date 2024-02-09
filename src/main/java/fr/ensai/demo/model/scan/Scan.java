package fr.ensai.demo.model.scan;

import fr.ensai.demo.model.filesystem.FileLeaf;
import java.util.Date;
import java.util.List;

public class Scan {
    private int id;
    private String fileSystemType;
    private String date;
    private String fileNameFilter;
    private String extensionFilter;
    private String rootDirectoryName;
    private int executionTime;
    private int size;
    private int maxFiles;
    private int maxDepth;
    private List<FileLeaf> scannedFiles;

    // Constructor
    public Scan(int id,
                String fileSystemType,
                String date,
                String fileNameFilter,
                String extensionFilter,
                String rootDirectoryName,
                int executionTime,
                int size,
                int maxFiles,
                int maxDepth,
                List<FileLeaf> scannedFiles) {

        this.id = id;
        this.fileSystemType = fileSystemType;
        this.date = date;
        this.fileNameFilter = fileNameFilter;
        this.extensionFilter = extensionFilter;
        this.rootDirectoryName = rootDirectoryName;
        this.executionTime = executionTime;
        this.size = size;
        this.maxFiles = maxFiles;
        this.maxDepth = maxDepth;
        this.scannedFiles = scannedFiles;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getFileSystemType() {
        return fileSystemType;
    }

    public String getDate() {
        return date;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getMaxFiles() {
        return maxFiles;
    }

    public int getSize() {
        return size;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public String getFileNameFilter() {
        return fileNameFilter;
    }

    public String getExtensionFilter() {
        return extensionFilter;
    }

    public String getRootDirectoryName() {
        return rootDirectoryName;
    }

    public List<FileLeaf> getScannedFiles() {
        return scannedFiles;
    }
}
