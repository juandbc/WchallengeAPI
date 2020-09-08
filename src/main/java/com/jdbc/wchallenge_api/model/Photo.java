package com.jdbc.wchallenge_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Document(collection = "photo")
public class Photo implements Serializable {

  private static final long serialVersionUID = -8653743686656633868L;

  @Transient
  public static final String SEQUENCE_NAME = "photo_sequence";

  @Id
  private int id;
  private String title;
  private String url;
  private String thumbnailUrl;
  private int albumId;

  public Photo() {
  }

  public Photo(int id, String title, String url, String thumbnailUrl, int albumId) {
    this.id = id;
    this.title = title;
    this.url = url;
    this.thumbnailUrl = thumbnailUrl;
    this.albumId = albumId;
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
  }

  public int getAlbumId() {
    return albumId;
  }

  public void setAlbumId(int albumId) {
    this.albumId = albumId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Photo photo = (Photo) o;
    return id == photo.id &&
            albumId == photo.albumId &&
            title.equals(photo.title) &&
            Objects.equals(url, photo.url) &&
            Objects.equals(thumbnailUrl, photo.thumbnailUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, url, thumbnailUrl, albumId);
  }

  @Override
  public String toString() {
    return "Photo{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", url='" + url + '\'' +
            ", thumbnailUrl='" + thumbnailUrl + '\'' +
            ", albumId=" + albumId +
            '}';
  }
}
