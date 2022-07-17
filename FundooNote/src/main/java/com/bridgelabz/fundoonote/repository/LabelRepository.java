package com.bridgelabz.fundoonote.repository;

import com.bridgelabz.fundoonote.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {

    Label findByLabelName(String labelName);

    Label findByLabelName(Label labelName);
}
