package com.adminportal.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

import javax.persistence.Transient;
@Entity
public class Word {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String vocabulary;
    private String phonetic;
    private String note;
    private String definition;
    private String typeword;
    private String title;
    @JsonIgnore
    @Transient
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
    @JsonIgnore
    private String imageWord;

    public Word(String vocabulary, String phonetic, String note, String definition, String typeword, String title, String imageWord) {
        this.vocabulary = vocabulary;
        this.phonetic = phonetic;
        this.note = note;
        this.definition = definition;
        this.typeword = typeword;
        this.title = title;
        this.imageWord = imageWord;
    }

    public Word() {
    }
    //    @ManyToOne
//    @JoinColumn(name="title_id",nullable = false)
//    private Title title;
//
//    @ManyToOne
//    @JoinColumn(name="type_id",nullable = false)
//    private Type type;


    public String getImageWord() {
        return imageWord;
    }

    public void setImageWord(String imageWord) {
        this.imageWord = imageWord;
    }

    public String getTypeword() {
        return typeword;
    }

    public void setTypeword(String typeword) {
        this.typeword = typeword;
    }



    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }





}
