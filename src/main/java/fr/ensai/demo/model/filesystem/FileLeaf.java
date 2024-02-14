package fr.ensai.demo.model.filesystem;

import fr.ensai.demo.model.scan.Scan;
import fr.ensai.demo.model.visitor.InterfaceFileSystemVisitor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "File")
public class FileLeaf implements InterfaceFileSystemComponent{

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

    @JsonIgnore
    @ManyToMany(
			mappedBy = "scannedFiles",
			cascade = CascadeType.ALL
			)
	private List<Scan> scan = new ArrayList<>();

    public FileLeaf() {
    }

    public FileLeaf(String name, String parentFolderName, String fileExtension, String modificationDate, long size, int depth) {
        this.name = name;
        this.parentFolderName = parentFolderName;
        this.fileExtension = fileExtension;
        this.modificationDate = modificationDate;
        this.size = size;
        this.depth = depth;
    }


    public void accept(InterfaceFileSystemVisitor visitor) {
        if(depth <= visitor.getMaxDepth()){
            visitor.visit(this);
        }

    }
    
    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentFolderName() {
        return parentFolderName;
    }

    public String getFileExtension() {
        return fileExtension;
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

    public List<Scan> getScan() {
        return scan;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentFolderName(String parentFolderName) {
        this.parentFolderName = parentFolderName;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setScan(List<Scan> scan) {
        this.scan = scan;
    }
}
