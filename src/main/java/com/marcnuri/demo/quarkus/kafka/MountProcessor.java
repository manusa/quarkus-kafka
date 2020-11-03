package com.marcnuri.demo.quarkus.kafka;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@ApplicationScoped
public class MountProcessor {

  private final Random random = new Random();

  @Incoming("mounts-inbox")
  @Outgoing("mounts-processed")
  @Broadcast
  public Mount process(String mountName) {
    System.out.println("Received mount: "+mountName);
    return new Mount(
      mountName,
      String.format("https://source.unsplash.com/featured/?%s", URLEncoder.encode(mountName, StandardCharsets.UTF_8)),
      (int)(random.nextInt(8849 - 1) + 1)
    );
  }
}
