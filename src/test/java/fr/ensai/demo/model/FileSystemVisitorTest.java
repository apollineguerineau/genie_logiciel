package fr.ensai.demo.model;

import fr.ensai.demo.model.filesystem.FileLeaf;
import fr.ensai.demo.model.visitor.FileSystemVisitor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSystemVisitorTest {

    @Test
    public void testVisit() {
        // Create some sample files
        FileLeaf file1 = new FileLeaf("file1.txt", "/path/to/directory", "txt", "2024-02-19", 1024, 10);
        FileLeaf file2 = new FileLeaf("file2.txt", "/path/to/directory", "txt", "2024-02-20", 2048, 1);
        FileLeaf file3 = new FileLeaf("file3.txt", "/path/to/directory", "txt", "2024-02-21", 3072, 1);
        FileLeaf file4 = new FileLeaf("file4.txt", "/path/to/directory", "txt", "2024-02-22", 3072, 1);


        // Create a FileSystemVisitor with filters
        FileSystemVisitor visitor = new FileSystemVisitor("file", "txt", 2, 2);

        // Visit the files
        visitor.visit(file1);
        visitor.visit(file2);
        visitor.visit(file3);
        visitor.visit(file4);

        // Get the visited files
        List<FileLeaf> visitedFiles = visitor.getVisitedFiles();

        // Assert that only file1 and file2 are visited due to depth and max files constraints
        assertEquals(2, visitedFiles.size());
        assertEquals("file2.txt", visitedFiles.get(0).getName());
        assertEquals("file3.txt", visitedFiles.get(1).getName());
    }
}
