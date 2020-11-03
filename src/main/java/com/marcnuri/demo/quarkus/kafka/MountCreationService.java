package com.marcnuri.demo.quarkus.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MountCreationService {

  private final Emitter<String> mountEmitter;

  @Inject
  public MountCreationService(@Channel("mounts-outbox") Emitter<String> mountEmitter) {
    this.mountEmitter = mountEmitter;
  }

  public void addMount(String mount) {
    mountEmitter.send(mount);
  }
}
