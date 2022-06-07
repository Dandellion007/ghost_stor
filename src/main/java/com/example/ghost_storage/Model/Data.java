package com.example.ghost_storage.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Data {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String codeName;
    private String name;
    private String OKCcode;
    private String OKPDcode;
    private Date adoptionDate;
    private Date introductionDate;
    private String developer;
    private String predecessor;
    private String contents;
    @Transient
    private List<String> keywords;
    @Transient
    private List<String> keyPhrases;
    private String levelOfAcceptance;
    @Transient
    private List<String> links;
    private String changes;
    private String status;
    private int referencesAmount;
    private String fileDesc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public Data() {
    }

    public Data(String name, String fileDesc, User user) {
        this.author = user;
        this.name = name;
        this.fileDesc = fileDesc;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
