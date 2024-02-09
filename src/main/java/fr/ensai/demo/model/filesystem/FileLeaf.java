package fr.ensai.demo.model.filesystem;

import fr.ensai.demo.model.visitor.InterfaceFileSystemVisitor; // Import the class from the other package

// Leaf class representing a file
public class FileLeaf implements InterfaceFileSystemComponent {
    private String name;
    private String parentFolderName;
    private String fileExtension;
    private String modificationDate;
    private long size;
    private int depth;

    public FileLeaf(String name,  
                    String parentFolderName, 
                    String fileExtension,
                    String modificationDate, 
                    long size,
                    int depth) {

        this.name = name;
        this.parentFolderName = parentFolderName;
        this.modificationDate = modificationDate;
        this.size = size;
        this.fileExtension = fileExtension;
        this.depth = depth;

    }

    public void accept(InterfaceFileSystemVisitor visitor) {
        if(depth <= visitor.getMaxDepth()){
            visitor.visit(this);
        }

    }

    public String getName() {
        return name;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getParentFolderName() {
        return parentFolderName;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public long getSize() {
        return size;
    }
    public int getDepth() {
        return depth;
    }
}
