package fr.ensai.demo.model;

import java.util.Date;

public class FileInfo {
    private String fileName;
    private Date modificationDate;
    private long fileSize;
    private String fileType;
    private String containingDirectory;

    // Constructeur
    public FileInfo(String fileName, Date modificationDate, long fileSize, String fileType, String containingDirectory) {
        this.fileName = fileName;
        this.modificationDate = modificationDate;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.containingDirectory = containingDirectory;
    }

    // Getters et Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getContainingDirectory() {
        return containingDirectory;
    }

    public void setContainingDirectory(String containingDirectory) {
        this.containingDirectory = containingDirectory;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "FileInfo{" +
                "fileName='" + fileName + '\'' +
                ", modificationDate=" + modificationDate +
                ", fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                ", containingDirectory='" + containingDirectory + '\'' +
                '}';
    }
}
