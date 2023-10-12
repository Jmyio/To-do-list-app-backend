package com.example.demo.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "liststasks") // Specify the desired table name here
public class ListsTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskid;

    @Column(nullable = false)
    private Long listid; // Replace list field with listid

    @Column(nullable = false)
    private String title;

    private String location;

    @Column(name = "fromdate", columnDefinition = "DATETIME")
    private LocalDateTime from;
    
    @Column(name = "todate", columnDefinition = "DATETIME")
    private LocalDateTime to;

    private java.sql.Time traveltime;

    @Lob
    private String notes;

    // Constructors, getters, and setters
    
    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public Long getListid() {
        return listid;
    }

    public void setListid(Long listid) {
        this.listid = listid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public java.sql.Time getTraveltime() {
        return traveltime;
    }

    public void setTraveltime(java.sql.Time traveltime) {
        this.traveltime = traveltime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
