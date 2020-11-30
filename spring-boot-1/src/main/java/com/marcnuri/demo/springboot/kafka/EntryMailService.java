package com.marcnuri.demo.springboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.function.UnaryOperator;

@Service
public class EntryMailService {

  private static final Logger log = LoggerFactory.getLogger(EntryMailService.class);

  private final String recipient;
  private final JavaMailSender mailSender;

  @Autowired
  public EntryMailService(JavaMailSender mailSender, @Value("${entry.mail.recipient}") String recipient) {
    this.mailSender = mailSender;
    this.recipient = recipient;
  }

  @KafkaListener(topics = "entries")
  public void processMessage(Map<String, Object> entry) throws MessagingException {
    sendMessage(entry);
  }

  private void sendMessage(Map<String, Object> entry) throws MessagingException {
    final UnaryOperator<String> eResolver = entryResolver(entry);
    log.info("New Entry Received: {}", eResolver.apply("name"));
    final MimeMessage demoMessage = mailSender.createMimeMessage();
    demoMessage.setFrom("demo@example.com");
    demoMessage.setRecipients(Message.RecipientType.TO, recipient);
    demoMessage.setSubject(String.format("New Entry Processed: %s", eResolver.apply("name")));
    demoMessage.setContent(
      String.format("New Entry%n%nName: %s%nDescription: %s",
        eResolver.apply("name"),
        eResolver.apply("description")
      ),
      "text/plain"
    );
    mailSender.send(demoMessage);
  }

  private static UnaryOperator<String> entryResolver(Map<String, Object> entry) {
    return key -> entry.getOrDefault(key, "n/a").toString();
  }

}
