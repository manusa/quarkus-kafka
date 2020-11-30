package com.marcnuri.demo.springboot.kafka;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

public class EntryDeserializer extends JsonDeserializer<Map<String, Object>> {
}
