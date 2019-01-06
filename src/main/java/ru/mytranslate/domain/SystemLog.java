package ru.mytranslate.domain;

import ru.mytranslate.controller.ValidLanguage;

import javax.persistence.*;

import java.util.Date;

@Entity
public class SystemLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;
    private String ip;
    private Date created;
    @Column(name = "source_lang", length = 10)
    @Enumerated(EnumType.STRING)
    private ValidLanguage sourceLanguage;
    @Column(name = "dest_lang", length = 10)
    @Enumerated(EnumType.STRING)
    private ValidLanguage destinationLanguage;
    @Column(length = 2000)
    private String text;

    public SystemLog() {
        created = new Date();
    }

    public SystemLog(String ip, ValidLanguage sourceLanguage, ValidLanguage destinationLanguage, String text) {
        this();
        this.ip = ip;
        this.sourceLanguage = sourceLanguage;
        this.destinationLanguage = destinationLanguage;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ValidLanguage getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(ValidLanguage sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public ValidLanguage getDestinationLanguage() {
        return destinationLanguage;
    }

    public void setDestinationLanguage(ValidLanguage destinationLanguage) {
        this.destinationLanguage = destinationLanguage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
