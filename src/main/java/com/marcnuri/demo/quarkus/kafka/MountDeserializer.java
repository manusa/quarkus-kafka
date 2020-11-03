package com.marcnuri.demo.quarkus.kafka;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MountDeserializer extends ObjectMapperDeserializer<Mount> {
  public MountDeserializer() {
    super(Mount.class);
  }
}
