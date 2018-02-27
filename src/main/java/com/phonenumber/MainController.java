package com.phonenumber;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    @RequestMapping(value="/phonenumber/{number}",produces = MediaType.APPLICATION_JSON_VALUE, 
            method = RequestMethod.GET)
    public ResponseEntity<Page<String>> phonenumberCombinations(@PathVariable("number") String number){
        PhoneNumberCombinations combination = new PhoneNumberCombinations();
        return new ResponseEntity<Page<String>>(combination.getCombinationsPageWise(number, 1, 10), 
                HttpStatus.OK);
    }
    
    @RequestMapping(value="/phonenumber/{number}/page/{page}/pageSize/{pageSize}",produces = MediaType.APPLICATION_JSON_VALUE, 
            method = RequestMethod.GET)
    public ResponseEntity<List<String>> phonenumberPage(@PathVariable("number") String number,
            @PathVariable("page") int page, @PathVariable("pageSize") int pageSize){
        PhoneNumberCombinations combination = new PhoneNumberCombinations();
        List<String> pageList = combination.getCombinationsPageSelected(number, page, pageSize);
        return new ResponseEntity<List<String>>(pageList, HttpStatus.OK);
    }
}
