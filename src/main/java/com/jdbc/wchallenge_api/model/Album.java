package com.jdbc.wchallenge_api.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public class Album implements Serializable {

  private static final long serialVersionUID = 2709850512506941409L;
  private int id;
  private String title;
  private List<Photo> photos;

  public Album() {}

  public Album(int id, String title, List<Photo> photos) {
    this.id = id;
    this.title = title;
    this.photos = photos;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Album album = (Album) o;
    return getId() == album.getId() &&
            getTitle().equals(album.getTitle()) &&
            Objects.equals(getPhotos(), album.getPhotos());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getPhotos());
  }

  @Override
  public String toString() {
    return "Album{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", photos=" + photos +
            '}';
  }
}
