package com.jdbc.wchallenge_api.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public class Post implements Serializable {

  private static final long serialVersionUID = 277641656384450461L;
  private int id;
  private String title;
  private String body;
  private int userId;

  public Post() {}

  public Post(int id, String title, String body, int userId) {
    this.id = id;
    this.title = title;
    this.body = body;
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

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Post post = (Post) o;
    return id == post.id &&
            title.equals(post.title) &&
            body.equals(post.body) &&
            Objects.equals(userId, post.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, body, userId);
  }

  @Override
  public String toString() {
    return "Post{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", body='" + body + '\'' +
            ", userId=" + userId +
            '}';
  }
}
