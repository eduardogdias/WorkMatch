package br.com.workmatchemail.workmatchemail.controller;

import br.com.workmatchemail.workmatchemail.dto.EmailDTO;
import br.com.workmatchemail.workmatchemail.entity.Email;
import br.com.workmatchemail.workmatchemail.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar-email")
    public ResponseEntity<Email> enviarEmail(@RequestBody @Valid EmailDTO emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        emailService.enviarEmail(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }

//    @GetMapping("/emails")
//    public ResponseEntity<Page<EmailModel>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable){
//        return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
//    }
//
//    @GetMapping("/emails/{emailId}")
//    public ResponseEntity<Object> getOneEmail(@PathVariable(value="emailId") UUID emailId){
//        Optional<EmailModel> emailModelOptional = emailService.findById(emailId);
//        if(!emailModelOptional.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
//        }else {
//            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
//        }
//    }
}