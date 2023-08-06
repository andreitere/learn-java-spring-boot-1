package com.learnspring.tere.demo.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "mails")
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID cateogry_id;

    public UUID getCateogry_id() {
        return cateogry_id;
    }

    public void setCateogry_id(UUID cateogry_id) {
        this.cateogry_id = cateogry_id;
    }

    private String sender;

    private String sent_date;

    public String getContent() {
        return content;
    }

    private String content;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFrom(String sender) {
        this.sender = sender;
    }

    public void setSent_date(String sent_date) {
        this.sent_date = sent_date;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
