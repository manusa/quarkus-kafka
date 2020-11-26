package com.marcnuri.demo.quarkus.kafka;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MountResource {

  private final MountCreationService mountCreationService;
  @Inject
  @RequestScoped
  @Channel("processed-mounts")
  Publisher<Mount> processedMounts;

  @Inject
  public MountResource(MountCreationService mountCreationService) {
    this.mountCreationService = mountCreationService;
  }

  @GET
  @Path("mounts")
  @Produces(MediaType.SERVER_SENT_EVENTS)
  @SseElementType(MediaType.APPLICATION_JSON)
  @Inject
  public Publisher<Mount> getMounts() {
    return processedMounts;
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
