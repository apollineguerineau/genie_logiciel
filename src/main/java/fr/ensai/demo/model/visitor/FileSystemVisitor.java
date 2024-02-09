package fr.ensai.demo.model.visitor;

import fr.ensai.demo.model.filesystem.FileLeaf; // Import the class from the other package
import fr.ensai.demo.model.filesystem.FolderComponent; // Import the class from the other package

import java.util.ArrayList;
import java.util.List;

// Concrete visitor class to get a list of files by name
public class FileSystemVisitor implements InterfaceFileSystemVisitor {
    private List<FileLeaf> visitedFiles;
    private String filenameFilter;
    private String extensionFilter;
    private int maxDepth;
    private int maxFiles;

    public FileSystemVisitor(String nameFilter, String extensionFilter, int maxDepth, int maxFiles) {
        visitedFiles = new ArrayList<>();
        this.filenameFilter = nameFilter;
        this.extensionFilter = extensionFilter;
        this.maxDepth = maxDepth;
        this.maxFiles = maxFiles;
    }

    public void visit(FileLeaf file) {
        if(file.getDepth() <= maxDepth && visitedFiles.size() < maxFiles){
            
            boolean isNameFilterVerified = true;
            boolean isExtensionFilterVerified = true;

            if (filenameFilter != null){
                isNameFilterVerified = file.getName().contains(filenameFilter);
            }

            if (extensionFilter != null){
                isExtensionFilterVerified = file.getName().contains(extensionFilter);
            } 
            
            if (isNameFilterVerified && isExtensionFilterVerified) {
                visitedFiles.add(file);
            }
        }
       
    }

    public void visit(FolderComponent directory) {}
   
    public int getMaxDepth() {
        return maxDepth;
    }

    public List<FileLeaf> getVisitedFiles() {
        return visitedFiles;
    }
}
