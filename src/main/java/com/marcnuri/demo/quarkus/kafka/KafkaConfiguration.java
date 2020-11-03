package com.marcnuri.demo.quarkus.kafka;


import io.smallrye.reactive.messaging.kafka.KafkaConnector;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.kafka.client.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.protocol.Message;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.HashMap;
import java.util.Map;

public class KafkaConfiguration {

//  @ConfigProperty(name = "kafka.bootstrap.servers", defaultValue = "localhost:9092")
//  String bootstrapServers;
//
//  @ConfigProperty(name = "kafka.zookeeper.server", defaultValue = "localhost:2181")
//  String zookeeperServer;
//
//
//  @Produces
//  @ApplicationScoped
//  public KafkaConsumer<String, Message> consumer(Vertx vertx) {
//    final Map<String, String> config = new HashMap<>();
//    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//    return KafkaConsumer.create(vertx, config);
//  }

}
