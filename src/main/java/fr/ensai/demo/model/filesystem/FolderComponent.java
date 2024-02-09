package fr.ensai.demo.model.filesystem;

import java.util.Date;
import java.text.*;
import fr.ensai.demo.model.visitor.InterfaceFileSystemVisitor; // Import the class from the other package

import java.util.ArrayList;
import java.util.List;
import java.io.File;

// Composite class representing a directory
public class FolderComponent implements InterfaceFileSystemComponent {
    private String name;
    private String absolutePath;
    private List<InterfaceFileSystemComponent> components;
    private int depth;
    

    public FolderComponent(String name, String absolutePath,int depth) {
        this.name = name;
        this.absolutePath = absolutePath;
        this.depth = depth;
        components = new ArrayList<>();
    }

    public void addComponent(InterfaceFileSystemComponent component) {
        components.add(component);
    }

    public void accept(InterfaceFileSystemVisitor visitor) {
        if (depth <= visitor.getMaxDepth()){
            visitor.visit(this);
            for (InterfaceFileSystemComponent component : components) {
                component.accept(visitor);
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public int getDepth() {
        return depth;
    }
}



