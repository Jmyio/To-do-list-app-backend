package com.example.demo.dto;

import jakarta.persistence.Id;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "lists") // Specify the desired table name here
public class Lists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listid;

    @Column(nullable = false)
    private Long userid; // Replace user field with userid

    @Column(nullable = false)
    private String listname;

    @Column(nullable = false)
    private boolean isdefault;

    // Constructors, getters, and setters
    
    public Long getListid() {
        return listid;
    }

    public void setListid(Long listid) {
        this.listid = listid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getListname() {
        return listname;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }

    public boolean isIsdefault() {
        return isdefault;
    }

    public void setIsdefault(boolean isdefault) {
        this.isdefault = isdefault;
    }
}
