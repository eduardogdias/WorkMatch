package br.com.workmatchemail.workmatchemail.controllers;


import br.com.workmatchemail.workmatchemail.dtos.EmailDto;
import br.com.workmatchemail.workmatchemail.models.Email;
import br.com.workmatchemail.workmatchemail.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/enviar-email")
    public ResponseEntity<Email> enviarEmail(@RequestBody @Valid EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        emailService.sendEmail(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }


}
