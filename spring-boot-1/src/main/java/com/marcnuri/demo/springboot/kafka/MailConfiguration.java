package com.marcnuri.demo.springboot.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

  @Bean
  public JavaMailSender javaMailSender() {
    final JavaMailSenderImpl demoSender = new JavaMailSenderImpl();
    demoSender.setHost("mail.mailinator.com");
    demoSender.setPort(25);
    demoSender.setProtocol("smtp");
    return demoSender;
  }
}
