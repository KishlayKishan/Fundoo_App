package com.bridgelabz.fundoonote.controller;

import com.bridgelabz.fundoonote.dto.NoteDto;
import com.bridgelabz.fundoonote.dto.ResponseDto;
import com.bridgelabz.fundoonote.model.Note;
import com.bridgelabz.fundoonote.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    INoteService service;


    @PostMapping("/addNote")
    public ResponseEntity<ResponseDto> createNote(@Valid @RequestBody NoteDto noteDto, @RequestHeader String token) {
        service.createNote(noteDto, token);
        ResponseDto response = new ResponseDto("Note is saved successfully..!", noteDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/viewNotes/{token}")
    public ResponseEntity<ResponseDto> viewNotes(@PathVariable String token) {
        List<Note> notes = service.viewNotes(token);
        ResponseDto response = new ResponseDto("Displaying Note Successfully", notes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/pinNote")
    public ResponseEntity<ResponseDto> pinNote(@RequestHeader String token, @RequestParam int noteId) {
        service.pinNote(token, noteId);
        ResponseDto response = new ResponseDto("Note Opertion Successfully", "");
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }

    @PutMapping("/archieveNote")
    public ResponseEntity<ResponseDto> archieveNote(@RequestHeader String token, @RequestParam int noteId) {
        service.archieveNote(token, noteId);
        ResponseDto response = new ResponseDto("Note Opertion Successfully", "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/toggleTrashNote")
    public ResponseEntity<ResponseDto> toggleTrashNote(@RequestHeader String token, @RequestParam int noteId) {
        service.toggleTrashNote(token, noteId);
        ResponseDto response = new ResponseDto("Note Trash operation succesfully", "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteNote")
    public ResponseEntity<ResponseDto> deleteNote(@RequestHeader String token, @RequestParam int noteId) {
        String message = service.deleteNote(token, noteId);
        ResponseDto response = new ResponseDto("Deletaion Successful", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
