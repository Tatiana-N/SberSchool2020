package org.nta.lessons.lesson15.solid;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailListener implements DataListener {

  String email;

  public EmailListener(String email) {
    this.email = email;
  }

  @Override
  public void onEvent(String result) {
    sendEmail(result, email);
  }

  public void sendEmail(String resulting, String recipients) {
    // now when the report is built we need to send it to the recipients list
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    // we're going to use google mail to send this message
    mailSender.setHost("mail.google.com");
    // construct the message
    MimeMessage message = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setTo(recipients);
      // setting message text, last parameter 'true' says that it is HTML format
      helper.setText(resulting, true);
      helper.setSubject("Monthly department salary report");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    // send the message
    mailSender.send(message);
  }
}



