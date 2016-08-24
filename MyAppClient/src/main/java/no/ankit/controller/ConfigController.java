package no.ankit.controller;

import no.ankit.config.MyAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AB75448 on 24.08.2016.
 */
@RestController
public class ConfigController {

    @Autowired
    private MyAppConfig appConfig;

    @RequestMapping(path = "/val")
    public ResponseEntity<?> getVal(){
        return new ResponseEntity<>(appConfig.getTest(), HttpStatus.OK);
    }

}
