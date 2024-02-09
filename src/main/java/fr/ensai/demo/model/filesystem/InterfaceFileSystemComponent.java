
package fr.ensai.demo.model.filesystem;

import fr.ensai.demo.model.visitor.InterfaceFileSystemVisitor; // Import the class from the other package

public interface InterfaceFileSystemComponent {
    void accept(InterfaceFileSystemVisitor visitor);
}