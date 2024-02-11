package fr.ensai.demo.model.filesystem;

import fr.ensai.demo.model.scan.Scan2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;


@Entity
@Table(name = "File")
public class FileLeaf2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_folder_name")
    private String parentFolderName;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "modification_date")
    private String modificationDate;

    @Column(name = "size")
    private long size;

    @Column(name = "depth")
    private int depth;

    @ManyToOne
    private Scan2 scan;

    public FileLeaf2() {
        // Implémentez-le selon vos besoins, ou laissez-le vide si aucune initialisation n'est nécessaire
    }

    // Constructeurs, getters et setters

    public FileLeaf2(String name, String parentFolderName, String fileExtension, String modificationDate,
                    long size, int depth) {
        this.name = name;
        this.parentFolderName = parentFolderName;
        this.fileExtension = fileExtension;
        this.modificationDate = modificationDate;
        this.size = size;
        this.depth = depth;
    }


    // Getters et setters
}
