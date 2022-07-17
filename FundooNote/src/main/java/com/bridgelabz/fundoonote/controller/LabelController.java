package com.bridgelabz.fundoonote.controller;

import com.bridgelabz.fundoonote.dto.ResponseDto;
import com.bridgelabz.fundoonote.model.Label;
import com.bridgelabz.fundoonote.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabelController {

    @Autowired
    LabelService labelService;

    @PostMapping("/createLabel")
    public ResponseEntity<ResponseDto> createLabel(@RequestHeader String token, @RequestBody String labelName) {
        Label label = labelService.createLable(token, labelName);
        ResponseDto responseDto = new ResponseDto("Created label succesfully",label);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/displayLabel")
    public ResponseEntity<ResponseDto> displayLabel(@RequestHeader String token) {
        List<Label> labels = labelService.displayAllLabels(token);
        ResponseDto responseDto = new ResponseDto("Displayed label succesfully",labels);
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteLabel")
    public ResponseEntity<ResponseDto>  deleteLabel(@RequestHeader String token, @RequestParam int labelId) {
        labelService.deleteLabel(token, labelId);
        ResponseDto responseDto = new ResponseDto("Label Deleted succesfully","");
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }

    @PutMapping("updateLabel")
    public ResponseEntity<ResponseDto>  updateLabel(@RequestHeader String token, @RequestParam int labelId, @RequestParam String labelName) {
        Label label = labelService.updateLabel(token, labelId, labelName);
        ResponseDto responseDto = new ResponseDto("Label Updated succesfully",label);
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }
}
