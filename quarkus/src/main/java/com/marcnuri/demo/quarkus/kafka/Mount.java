package com.marcnuri.demo.quarkus.kafka;

public class Mount {
  private final String name;
  private final String image;
  private final int elevation;

  public Mount(String name, String image, int elevation) {
    this.name = name;
    this.image = image;
    this.elevation = elevation;
  }

  public String getName() {
    return name;
  }

  public String getImage() {
    return image;
  }

  public int getElevation() {
    return elevation;
  }
}
