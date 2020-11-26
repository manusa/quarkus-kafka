package com.marcnuri.demo.quarkus.kafka;

public class Entry {
  private final String name;
  private final String description;
  private final String originalQuery;
  private final String image;

  public Entry(String name, String description, String originalQuery, String image) {
    this.name = name;
    this.description = description;
    this.originalQuery = originalQuery;
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getOriginalQuery() {
    return originalQuery;
  }

  public String getImage() {
    return image;
  }
}
