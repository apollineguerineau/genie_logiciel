package fr.ensai.demo.model.scan;

import fr.ensai.demo.model.filesystem.FileLeaf;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "Scan")

public class Scan {

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
    private float executionTime;

    @Column(name="size")
    private int size;

    @Column(name="max_files")
    private int maxFiles;

    @Column(name="max_depth")
    private int maxDepth;

    @ManyToMany(
			fetch = FetchType.LAZY,
			cascade = { 
					CascadeType.PERSIST, 
					CascadeType.MERGE 
					}	
			)
	@JoinTable(
			name = "FileScanAssoc",
			joinColumns = @JoinColumn(name = "scan_id"), 
			inverseJoinColumns = @JoinColumn(name = "file_id")
			)
	private List<FileLeaf> scannedFiles = new ArrayList<>();	

    public Scan() {
    }

    public Scan(String fileSystemType, String scanDate, String fileNameFilter, String extensionFilter,
                String rootDirectoryName, float executionTime, int size, int maxFiles, int maxDepth, List<FileLeaf> scannedFiles) {
        this.fileSystemType = fileSystemType;
        this.scanDate = scanDate;
        this.fileNameFilter = fileNameFilter;
        this.extensionFilter = extensionFilter;
        this.rootDirectoryName = rootDirectoryName;
        this.executionTime = executionTime;
        this.size = size;
        this.maxFiles = maxFiles;
        this.maxDepth = maxDepth;
        this.scannedFiles = scannedFiles;
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

    public float getExecutionTime() {
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

    public List<FileLeaf> getScannedFiles() {
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

    public void setExecutionTime(float executionTime) {
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

    public void addScannedFile(FileLeaf fileLeaf) {
        this.scannedFiles.add(fileLeaf);
    }

    public void setScannedFiles(List<FileLeaf> scannedFiles) {
        this.scannedFiles = scannedFiles;
    }

    public void setOneScannedFile(FileLeaf newScannedFile, FileLeaf oldScannedFile) {
        // Retirer l'ancien fichier s'il est présent
        if (oldScannedFile != null) {
            this.scannedFiles.remove(oldScannedFile);
        }
        // Ajouter le nouveau fichier
        this.scannedFiles.add(newScannedFile);
}
}

