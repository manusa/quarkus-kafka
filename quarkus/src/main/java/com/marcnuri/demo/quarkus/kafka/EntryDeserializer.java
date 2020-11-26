package com.marcnuri.demo.quarkus.kafka;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class EntryDeserializer extends ObjectMapperDeserializer<Entry> {
  public EntryDeserializer() {
    super(Entry.class);
  }
}
