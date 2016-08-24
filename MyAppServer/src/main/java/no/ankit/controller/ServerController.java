package no.ankit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AB75448 on 24.08.2016.
 */
@RestController
public class ServerController {

    @RequestMapping(path="/serviceToInvoke")
    public ResponseEntity<?> getResult(){
        return new ResponseEntity<String>("Value from server", HttpStatus.OK);
    }
}
