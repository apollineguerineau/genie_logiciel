package fr.ensai.demo.model.visitor;

import fr.ensai.demo.model.filesystem.FileLeaf; // Import the class from the other package
import fr.ensai.demo.model.filesystem.FolderComponent; // Import the class from the other package

// Visitor interface
public interface InterfaceFileSystemVisitor {
    void visit(FileLeaf file);
    void visit(FolderComponent directory);
    public int getMaxDepth();
}
