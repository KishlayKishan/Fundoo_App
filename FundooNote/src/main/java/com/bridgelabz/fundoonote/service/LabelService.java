package com.bridgelabz.fundoonote.service;

import com.bridgelabz.fundoonote.exception.FunDoNotesCustomException;
import com.bridgelabz.fundoonote.model.Label;
import com.bridgelabz.fundoonote.model.User;
import com.bridgelabz.fundoonote.repository.LabelRepository;
import com.bridgelabz.fundoonote.repository.NoteRepository;
import com.bridgelabz.fundoonote.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabelService implements ILabelService{

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    LabelRepository labelRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Label createLable(String token, String labelName) {
        User user = gettingUser(token);
        Label label = labelRepository.findByLabelName(labelName);
        if (label != null) {
            throw new FunDoNotesCustomException(HttpStatus.CONFLICT, "Label Name already presnt");
        }
        Label label1 = new Label();
        label1.setLabelName(labelName);
        label1.setUserId(user.getUserId());
        labelRepository.save(label1);

        return label1;
    }

    private User gettingUser(String token) {
        User user = restTemplate.getForObject("http://localhost:9002/user/findById/"+token, User.class);
        if (user == null) {
            throw new FunDoNotesCustomException(HttpStatus.BAD_REQUEST,"User not found");
        }
        return user;
    }

    @Override
    public List<Label> displayAllLabels(String token){
        User user = gettingUser(token);
        List<Label> labels = labelRepository.findAll().stream().
                filter(label -> label.getUserId()==user.getUserId()).collect(Collectors.toList());
        return labels;
    }


    @Override
    public void deleteLabel(String token, int labelId) {
        User user = gettingUser(token);
        Label labels = labelRepository.findAll().stream().filter(label -> label.getUserId()==user.getUserId()).findAny().get();
        if (labels == null) {
            throw new FunDoNotesCustomException(HttpStatus.BAD_REQUEST,"Label not created");
        }
        labelRepository.delete(labels);
    }

    @Override
    public Label updateLabel(String token, int labelId, String labelName) {
        User user = gettingUser(token);

        Label labels = labelRepository.findAll().stream().filter(label -> label.getUserId()==user.getUserId()).findAny().get();
        if (labels == null) {
            throw new FunDoNotesCustomException(HttpStatus.BAD_REQUEST,"Label not created");
        }

        labels.setLabelName(labelName);
        labelRepository.save(labels);
        return labels;
    }
}
