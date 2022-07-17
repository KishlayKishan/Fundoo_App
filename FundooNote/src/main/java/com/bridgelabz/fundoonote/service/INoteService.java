package com.bridgelabz.fundoonote.service;

import com.bridgelabz.fundoonote.dto.NoteDto;
import com.bridgelabz.fundoonote.model.Note;

import java.util.List;

public interface INoteService {

    Note createNote(NoteDto nodeDto, String token);

    List<Note> viewNotes(String token);

    public Note pinNote(String token, int noteId);

    public Note archieveNote(String token, int noteId);

    public String deleteNote(String token, int noteId);

    public Note toggleTrashNote(String token, int noteId);
}
