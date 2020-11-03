package com.marcnuri.demo.quarkus.kafka;

import io.reactivex.Flowable;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class KafkaResource {

  private final MountCreationService mountCreationService;
  private final MountConsumerService mountConsumerService;

  @Inject
  public KafkaResource(MountCreationService mountCreationService, MountConsumerService mountConsumerService) {
    this.mountCreationService = mountCreationService;
    this.mountConsumerService = mountConsumerService;
  }

  @GET
  @Path("mounts")
  @Produces(MediaType.SERVER_SENT_EVENTS)
  @SseElementType(MediaType.APPLICATION_JSON)
  @Inject
  public Publisher<String> getMounts() {
    return mountConsumerService.getMounts();
  }

  @POST
  @Path("mounts")
  @Consumes(MediaType.TEXT_PLAIN)
  public void newMount(String mount) {
    mountCreationService.addMount(mount);
  }

  @GET
  @Path("test")
  @Produces(MediaType.SERVER_SENT_EVENTS)
  @SseElementType(MediaType.APPLICATION_JSON)
  @Inject
  public Publisher<Mount> getTest() {
    return Flowable.fromArray(
      new Mount("mount-1", "https://tailwindcss.com/img/card-top.jpg", 1),
      new Mount("mount-2", "https://tailwindcss.com/img/card-top.jpg", 2)
    );
  }
}
