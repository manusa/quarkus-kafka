package com.marcnuri.demo.quarkus.kafka;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class EntryProcessor {

  @Inject
//  @RestClient
  WikipediaService wikipediaService;

  @Incoming("queries")
  @Outgoing("entries-out")
  @Broadcast
  public Entry process(String query) {
    final Optional<Map<String, Object>> wikipediaInfo = getWikipediaPageSummary(query);
    return new Entry(
      wikipediaInfo.map(info -> info.get("titles"))
        .map(titles -> ((Map<String, ?>)titles).get("display")).map(Object::toString).orElse(query),
      wikipediaInfo.map(info -> info.get("description")).map(Object::toString).orElse("n/a"),
      query,
      getRedirectUrl(query)
    );
  }

  private Optional<Map<String, Object>> getWikipediaPageSummary(String query) {
    try {
      return Optional.of(wikipediaService.getPageSummary(query, true));
    } catch (Exception ex) {
      return Optional.empty();
    }
  }

  private static String getRedirectUrl(String mountName) {
    final String originalURL = String.format("https://source.unsplash.com/featured/?%s",
      URLEncoder.encode(mountName, StandardCharsets.UTF_8));
    try {
      URLConnection uc = new URL(originalURL).openConnection();
      uc.connect();
      try(final InputStream ignore = uc.getInputStream()) {
        return uc.getURL().toString();
      }
    } catch(IOException ex) {
      // Ignore
    }
    return originalURL;
  }
}
