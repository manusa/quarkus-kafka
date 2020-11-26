package com.marcnuri.demo.quarkus.kafka;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient43Engine;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.ws.rs.client.ClientBuilder;
import java.net.URI;

@ApplicationScoped
public class Configuration {

  @Produces
  @ApplicationScoped
  public WikipediaService wikipediaService() {
    final ApacheHttpClient43Engine engine = new ApacheHttpClient43Engine();
    engine.setFollowRedirects(true);
    return ((ResteasyClientBuilder)ClientBuilder.newBuilder()).httpEngine(engine).build()
      .target(URI.create("https://en.wikipedia.org"))
      .proxy(WikipediaService.class);
    // TODO: Follow redirects not allowed until microprofile-rest-client 2.x
    // Must manually configure underlying engine
//    return RestClientBuilder.newBuilder()
//      .baseUri(URI.create("https://en.wikipedia.org"))
//      .build(WikipediaService.class);
  }
}
