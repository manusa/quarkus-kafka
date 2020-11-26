package com.marcnuri.demo.quarkus.kafka;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/queries")
public class QueryResource {

  private final QueryCreationService queryCreationService;

  @Inject
  public QueryResource(QueryCreationService queryCreationService) {
    this.queryCreationService = queryCreationService;
  }

  @POST
  @Path("/")
  @Consumes(MediaType.TEXT_PLAIN)
  public void newQuery(String query) {
    queryCreationService.newQuery(query);
  }
}
