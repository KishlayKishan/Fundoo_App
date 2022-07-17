package com.bridgelabz.fundoonote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    private Boolean isPinned;

    private Boolean isTrashed;

    private Boolean isArchived;

    private String colour;

    private String reminder;
}
