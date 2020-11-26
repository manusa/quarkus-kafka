package com.marcnuri.demo.quarkus.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/entries")
public class EntryResource {

  @Inject
  @RequestScoped
  @Channel("entries")
  Publisher<Entry> processedEntries;

  @GET
  @Path("/")
  @Produces(MediaType.SERVER_SENT_EVENTS)
  @SseElementType(MediaType.APPLICATION_JSON)
  @Inject
  public Publisher<Entry> getEntries() {
    return processedEntries;
  }
}
