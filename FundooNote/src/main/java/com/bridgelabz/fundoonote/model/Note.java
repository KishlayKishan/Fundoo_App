package com.bridgelabz.fundoonote.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "NoteDetails")
public class Note {
    @Id
    @GeneratedValue
    private int id;

    private String title;

    private String description;

    private Boolean isPinned = false;

    private Boolean isArchived = false;

    private Boolean isTrashed = false;

    private String colour;

    private Date reminder;

    private int userId;

    @CreationTimestamp
    private LocalDateTime createdTimeStamp;
    @UpdateTimestamp
    private LocalDateTime updateTimeStamp;

    @ManyToMany
    @JoinTable( name = "Label_Name",
            joinColumns = { @JoinColumn (name = "Note_id")},
            inverseJoinColumns = { @JoinColumn (name = "label_id") })
    private List<Label> labels;


}
