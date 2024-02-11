package fr.ensai.demo.model.scan;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.filesystem.FileLeaf2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "Scan")

public class Scan2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name="file_system_type")
    private String fileSystemType;

    @Column(name="scan_date")
    private String scanDate;

    @Column(name="file_name_filter")
    private String fileNameFilter;

    @Column(name="extension_filter")
    private String extensionFilter;

    @Column(name="root_directory_name")
    private String rootDirectoryName;

    @Column(name="execution_time")
    private int executionTime;

    @Column(name="size")
    private int size;

    @Column(name="max_files")
    private int maxFiles;

    @Column(name="max_depth")
    private int maxDepth;

    @OneToMany(mappedBy = "scan", cascade = CascadeType.ALL)
    private List<FileLeaf2> scannedFiles;

    public Scan2() {
        // Implémentez-le selon vos besoins, ou laissez-le vide si aucune initialisation n'est nécessaire
    }

    public Scan2(String fileSystemType, String scanDate, String fileNameFilter, String extensionFilter,
                String rootDirectoryName, int executionTime, int size, int maxFiles, int maxDepth) {
        this.fileSystemType = fileSystemType;
        this.scanDate = scanDate;
        this.fileNameFilter = fileNameFilter;
        this.extensionFilter = extensionFilter;
        this.rootDirectoryName = rootDirectoryName;
        this.executionTime = executionTime;
        this.size = size;
        this.maxFiles = maxFiles;
        this.maxDepth = maxDepth;
        this.scannedFiles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getFileSystemType() {
        return fileSystemType;
    }

    public String getScanDate() {
        return scanDate;
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

    public int getExecutionTime() {
        return executionTime;
    }

    public int getSize() {
        return size;
    }

    public int getMaxFiles() {
        return maxFiles;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public List<FileLeaf2> getScannedFiles() {
        return scannedFiles;
    }


    public void setFileSystemType(String fileSystemType) {
        this.fileSystemType = fileSystemType;
    }

    public void setScanDate(String scanDate) {
        this.scanDate = scanDate;
    }

    public void setFileNameFilter(String fileNameFilter) {
        this.fileNameFilter = fileNameFilter;
    }

    public void setExtensionFilter(String extensionFilter) {
        this.extensionFilter = extensionFilter;
    }

    public void setRootDirectoryName(String rootDirectoryName) {
        this.rootDirectoryName = rootDirectoryName;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setMaxFiles(int maxFiles) {
        this.maxFiles = maxFiles;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void addScannedFile(FileLeaf2 fileLeaf) {
        this.scannedFiles.add(fileLeaf);
    }

    public void setScannedFiles(List<FileLeaf2> scannedFiles) {
        this.scannedFiles = scannedFiles;
    }
}

