package com.marcnuri.demo.quarkus.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.reactivestreams.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MountConsumerService {

  private final Publisher<String> mounts;

  @Inject
  public MountConsumerService(@Channel("mounts-processed") Publisher<String> mounts) {
    this.mounts = mounts;
  }

  public Publisher<String> getMounts() {
    return mounts;
  }
}
