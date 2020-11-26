package com.marcnuri.demo.quarkus.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class QueryCreationService {

  private final Emitter<String> queryEmitter;

  @Inject
  public QueryCreationService(@Channel("queries-out") Emitter<String> queryEmitter) {
    this.queryEmitter = queryEmitter;
  }

  public void newQuery(String query) {
    queryEmitter.send(query);
  }
}
