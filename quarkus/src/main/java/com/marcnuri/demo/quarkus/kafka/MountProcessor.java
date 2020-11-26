package com.marcnuri.demo.quarkus.kafka;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@ApplicationScoped
public class MountProcessor {

  private final Random random = new Random();

  @Incoming("mounts")
  @Outgoing("processed-mounts-out")
  @Broadcast
  public Mount process(String mountName) {
    return new Mount(mountName, getRedirectUrl(mountName),random.nextInt(8849 - 1) + 1);
  }

  private static String getRedirectUrl(String mountName) {
    final String originalURL = String.format("https://source.unsplash.com/featured/?%s",
      URLEncoder.encode(mountName, StandardCharsets.UTF_8));
    try {
      URLConnection uc = new URL(originalURL).openConnection();
      uc.connect();
      try(final InputStream ignore = uc.getInputStream()) {
        return uc.getURL().toString();
      }
    } catch(IOException ex) {
      // Ignore
    }
    return originalURL;
  }
}
