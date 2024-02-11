// package fr.ensai.demo.model;

// import fr.ensai.demo.model.scan.*;
// import fr.ensai.demo.model.filesystem.*;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.CascadeType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// import java.util.ArrayList;
// import java.util.List;

// @Entity
// @Table(name = "FileScanAssoc")
// public class FileScanAssoc {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "scan_id", referencedColumnName = "id")
//     private Scan scan;

//     @ManyToOne
//     @JoinColumn(name = "file_id", referencedColumnName = "id")
//     private FileLeaf fileLeaf;

//     // Constructeur, getters et setters
//     public FileScanAssoc() {
//     }

//     public FileScanAssoc(Scan scan, FileLeaf fileLeaf) {
//         this.scan = scan;
//         this.fileLeaf = fileLeaf;
//     }

//     // Getters and setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Scan getScan() {
//         return scan;
//     }

//     public void setScan(Scan scan) {
//         this.scan = scan;
//     }

//     public FileLeaf getFileLeaf() {
//         return fileLeaf;
//     }

//     public void setFileLeaf(FileLeaf fileLeaf) {
//         this.fileLeaf = fileLeaf;
//     }
// }
