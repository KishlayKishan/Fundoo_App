package com.bridgelabz.fundoonote.service;


import com.bridgelabz.fundoonote.model.Label;

import java.util.List;

public interface ILabelService {

    com.bridgelabz.fundoonote.model.Label createLable(String token, String labelName);

    List<Label> displayAllLabels(String token);

    void deleteLabel(String token, int labelId);

    Label updateLabel(String token, int labelId, String labelName);
}
