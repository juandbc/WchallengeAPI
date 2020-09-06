package com.jdbc.wchallenge_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Document(collection = "album")
public class Album implements Serializable {

  private static final long serialVersionUID = 2709850512506941409L;

  @Id
  private int id;
  private String title;
  private int userId;

  public Album() {
  }

  public Album(int id, String title, int userId) {
    this.id = id;
    this.title = title;
    this.userId = userId;
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

  public int getUserId() {
    return userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Album album = (Album) o;
    return id == album.id &&
            userId == album.userId &&
            title.equals(album.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, userId);
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Album{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", userId=" + userId +
            '}';
  }
}
