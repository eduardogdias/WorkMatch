package br.com.workmatchemail.workmatchemail.services;

import br.com.workmatchemail.workmatchemail.models.Email;
import br.com.workmatchemail.workmatchemail.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public Email sendEmail(Email emailModel) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);


        } catch (MailException e){
            System.out.println("Erro ao enviar email: " + e);
        } finally {
            return emailRepository.save(emailModel);
        }
    }


}
