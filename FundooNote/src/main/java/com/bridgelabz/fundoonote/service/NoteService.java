package com.bridgelabz.fundoonote.service;

import com.bridgelabz.fundoonote.dto.NoteDto;
import com.bridgelabz.fundoonote.exception.NoteException;
import com.bridgelabz.fundoonote.model.Note;
import com.bridgelabz.fundoonote.model.User;
import com.bridgelabz.fundoonote.repository.NoteRepository;
import com.bridgelabz.fundoonote.util.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 15;
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public Note createNote(NoteDto nodeDto, String token) {
        User user = gettingUser(token);
        Note note = new Note();
        BeanUtils.copyProperties(nodeDto, note);
        note.setUserId(user.getUserId());
        noteRepository.save(note);
        return note;
    }

    private User gettingUser(String token) {
        User user = restTemplate.getForObject("http://localhost:9002/user/findById/" + token, User.class);
        if (user == null) {
            throw new NoteException(HttpStatus.BAD_REQUEST, "User Not Found");
        }
        return user;
    }

    @Override
    public List<Note> viewNotes(String token) {
        User user = gettingUser(token);
        List<Note> notes = noteRepository.findAll().stream().filter(note -> (note.getIsArchived().equals(false|true))
                && (note.getUserId() == user.getUserId())).collect(Collectors.toList());
        return notes;
    }

    @Override
    public Note pinNote(String token, int noteId) {
        Optional<Note> note = Optional.of(gettingNote(token, noteId));
        if (note.get().getIsPinned()) {
           throw new NoteException("No need to pin again");
        } else {
            note.get().setIsPinned(true);
            noteRepository.save(note.get());
        }
        return note.get();
    }

    private Note gettingNote(String token, int noteId) {
        User user = gettingUser(token);
        Note note = noteRepository.findAll().stream().filter(notes -> ((notes.getId() == noteId) && (notes.getUserId() == user.getUserId()))).findAny().get();
        if (note == null) {
            throw new NoteException(HttpStatus.BAD_REQUEST, "User not found for creating notes");
        }
        return note;
    }

    @Override
    public Note archieveNote(String token, int noteId) {
        Optional<Note> note = Optional.of(gettingNote(token, noteId));

        if (note.get().getIsArchived()){
            throw new NoteException("No Need To Archive Again");
        }else {
            note.get().setIsArchived(true);
            noteRepository.save(note.get());
        }
        return note.get();
    }

    @Override
    public Note toggleTrashNote(String token, int noteId) {
        Optional<Note> note = Optional.of(gettingNote(token, noteId));

        if (note.get().getIsTrashed()){
            throw new NoteException("No Need To Trash Again");
        }else {
            note.get().setIsTrashed(true);
            noteRepository.save(note.get());
        }
        return note.get();
    }

    @Override
    public String deleteNote(String token, int noteId) {
        Note note = gettingNote(token, noteId);
        LocalDateTime tokenCreationDate = note.getCreatedTimeStamp();
        if (note.getIsTrashed() | isTokenExpired(tokenCreationDate)){
            noteRepository.delete(note);
            return "Deleted successfully";
        }
        else {
            return "First move to trash";
        }
    }

    /**
     * Check whether the created token expired or not.
     *
     * @param tokenCreationDate
     * @return true or false
     */
    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }


}
