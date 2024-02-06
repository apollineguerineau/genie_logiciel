package fr.ensai.demo.model;

import java.util.Date;
import java.util.List;

public class Scan {
    private int scanId;
    private int maxNumberOfFiles;
    private int maxDepth;
    private String fileNameFilter;
    private String fileTypeFilter;
    private Date scanDate;
    private long executionTime;
    private List<FileInfo> fileInfos;

    // Constructeur
    public Scan(int scanId, int maxNumberOfFiles, int maxDepth, String fileNameFilter, String fileTypeFilter,
                Date scanDate, long executionTime, List<FileInfo> fileInfos) {
        this.scanId = scanId;
        this.maxNumberOfFiles = maxNumberOfFiles;
        this.maxDepth = maxDepth;
        this.fileNameFilter = fileNameFilter;
        this.fileTypeFilter = fileTypeFilter;
        this.scanDate = scanDate;
        this.executionTime = executionTime;
        this.fileInfos = fileInfos;
    }

    // Getters et Setters
    public int getScanId() {
        return scanId;
    }

    public void setScanId(int scanId) {
        this.scanId = scanId;
    }

    public int getMaxNumberOfFiles() {
        return maxNumberOfFiles;
    }

    public void setMaxNumberOfFiles(int maxNumberOfFiles) {
        this.maxNumberOfFiles = maxNumberOfFiles;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public String getFileNameFilter() {
        return fileNameFilter;
    }

    public void setFileNameFilter(String fileNameFilter) {
        this.fileNameFilter = fileNameFilter;
    }

    public String getFileTypeFilter() {
        return fileTypeFilter;
    }

    public void setFileTypeFilter(String fileTypeFilter) {
        this.fileTypeFilter = fileTypeFilter;
    }

    public Date getScanDate() {
        return scanDate;
    }

    public void setScanDate(Date scanDate) {
        this.scanDate = scanDate;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public List<FileInfo> getFileInfos() {
        return fileInfos;
    }

    public void setFileInfos(List<FileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Scan{" +
                "scanId=" + scanId +
                ", maxNumberOfFiles=" + maxNumberOfFiles +
                ", maxDepth=" + maxDepth +
                ", fileNameFilter='" + fileNameFilter + '\'' +
                ", fileTypeFilter='" + fileTypeFilter + '\'' +
                ", scanDate=" + scanDate +
                ", executionTime=" + executionTime +
                ", fileInfos=" + fileInfos +
                '}';
    }
}

