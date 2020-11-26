package com.marcnuri.demo.quarkus.kafka;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Map;

@Path("/api/rest_v1")
//@RegisterRestClient(configKey = "wikipedia-api")
public interface WikipediaService {

  @GET
  @Path("/page/summary/{title}")
  @Produces("application/json")
  Map<String, Object> getPageSummary(
    @PathParam("title") String title,
    @QueryParam("redirect") boolean redirect
  );
}
