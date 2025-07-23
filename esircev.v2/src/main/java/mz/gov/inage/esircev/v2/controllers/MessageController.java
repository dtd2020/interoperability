package mz.gov.inage.esircev.v2.controllers;

import mz.gov.inage.esircev.v2.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @RequestMapping("/hello")
    public Message testingRaw(){
        return new Message("Nada de Olá mundo Wontas!");
    }
}
