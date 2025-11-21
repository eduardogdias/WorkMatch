package br.com.workmatchemail.workmatchemail.service;

import br.com.workmatchemail.workmatchemail.entity.Email;
import br.com.workmatchemail.workmatchemail.repository.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public Email enviarEmail(Email email) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

        } catch (MailException e){
            System.out.println("Erro ao enviar email: " + e);
        } finally {
            return emailRepository.save(email);
        }
    }

//    public Page<EmailModel> findAll(Pageable pageable) {
//        return  emailRepository.findAll(pageable);
//    }
//
//    public Optional<EmailModel> findById(UUID emailId) {
//        return emailRepository.findById(emailId);
//    }
}