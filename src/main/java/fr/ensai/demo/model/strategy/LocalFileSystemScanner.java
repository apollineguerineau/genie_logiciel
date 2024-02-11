package fr.ensai.demo.model.strategy;

import fr.ensai.demo.model.scan.Scan;
import fr.ensai.demo.model.visitor.FileSystemVisitor;
import fr.ensai.demo.model.filesystem.*;
import java.io.File; 
import java.util.Date;
import java.util.List;
import java.text.*;

public class LocalFileSystemScanner implements InterfaceFileSystemScannerStrategy {

    @Override
    public FolderComponent importFileSystem(String path, int currentDepth) {
        File folder = new File(path);
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("The provided path is not a directory.");
        }
        return importFileSystemRecursively(folder, currentDepth);
    }

    @Override
    public Scan scanFileSystem(FolderComponent folder, String filenameFilter, String extensionFilter, int maxFiles, int maxDepth) {
        FileSystemVisitor visitor = new FileSystemVisitor(filenameFilter, extensionFilter, maxDepth, maxFiles);
        folder.accept(visitor);
        List<FileLeaf> visitedFiles = visitor.getVisitedFiles();
        int size = 0;
        for (FileLeaf file : visitedFiles) {
            size+=(file.getSize());
        };
        Scan scan = new Scan(0, "local file system", "11/02", filenameFilter, extensionFilter, folder.getName(), 0, size, maxFiles, maxDepth, visitedFiles);
        return scan; // Placeholder, replace with actual implementation
    }

    private FolderComponent importFileSystemRecursively(File folder, int currentDepth) {
        FolderComponent currentFolder = new FolderComponent(folder.getName(), folder.getAbsolutePath(), currentDepth);

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    FolderComponent subFolder = importFileSystemRecursively(file, currentDepth + 1);
                    currentFolder.addComponent(subFolder);
                } else {
                    currentFolder.addComponent(new FileLeaf(file.getName(),
                                                             file.getParentFile().getAbsolutePath(),
                                                             parseExtension(file.getName()),
                                                             convertDate(file.lastModified()),
                                                             file.length(),
                                                             currentDepth));
                }
            }
        }
        return currentFolder;
    }


    public String parseExtension(String fileName){
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }

    public String convertDate(long longDate){
        Date date = new Date(longDate);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }

}
