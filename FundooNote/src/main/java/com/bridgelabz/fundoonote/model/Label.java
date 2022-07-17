package com.bridgelabz.fundoonote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "labels")
public class Label {
    @Id
    @GeneratedValue
    private int id;

    private String labelName;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime tokenCreationDate;

    @UpdateTimestamp
    private LocalDateTime labelUpdated;

    @ManyToMany(mappedBy = "labels")
    private List<Note> notes;

    private int userId;
}
