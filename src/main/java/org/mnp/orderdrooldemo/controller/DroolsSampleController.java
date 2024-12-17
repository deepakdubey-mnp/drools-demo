package org.mnp.orderdrooldemo.controller;

import org.mnp.orderdrooldemo.config.DroolsAppConfig;
import org.mnp.orderdrooldemo.services.DroolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  org.mnp.orderdrooldemo.model.Participant;
import org.mnp.orderdrooldemo.model.Rate;

@RestController()
@RequestMapping("/bankservice")
public class DroolsSampleController {

    @Autowired
    private DroolsService bankService;


    @PostMapping("/getrate")
    public ResponseEntity<Rate> getRate(@RequestBody Participant request){
        Rate rate = bankService.getRate(request);
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }
    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(){
        DroolsAppConfig.refresh();
        return new ResponseEntity<>("Refreshed", HttpStatus.OK);
    }

}