package ftn.kts.controller;

import ftn.kts.model.Panic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/panic")
public class PanicController {



    @GetMapping
    public ResponseEntity<List<Panic>> getAllPanicNotifications() {

        return null;
    }
}
