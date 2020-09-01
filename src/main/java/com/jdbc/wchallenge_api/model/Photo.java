package com.jdbc.wchallenge_api.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public class Photo implements Serializable {

  private static final long serialVersionUID = -1845721293948765682L;
  private int id;
  private String title;
  private String url;
  private String thumbnailUrl;

  public Photo() {}

  public Photo(int id, String title, String url, String thumbnailUrl) {
    this.id = id;
    this.title = title;
    this.url = url;
    this.thumbnailUrl = thumbnailUrl;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Photo photo = (Photo) o;
    return id == photo.id &&
            title.equals(photo.title) &&
            url.equals(photo.url) &&
            thumbnailUrl.equals(photo.thumbnailUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, url, thumbnailUrl);
  }

  @Override
  public String toString() {
    return "Photo{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", url='" + url + '\'' +
            ", thumbnailUrl='" + thumbnailUrl + '\'' +
            '}';
  }
}
