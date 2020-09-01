package com.jdbc.wchallenge_api.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
public class Comment implements Serializable {

  private static final long serialVersionUID = 1990586977481539117L;
  private int id;
  private String name;
  private String email;
  private String body;

  public Comment() {}

  public Comment(int id, String name, String email, String body) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.body = body;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Comment comment = (Comment) o;
    return id == comment.id &&
            name.equals(comment.name) &&
            email.equals(comment.email) &&
            body.equals(comment.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, body);
  }

  @Override
  public String toString() {
    return "Comment{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", body='" + body + '\'' +
            '}';
  }
}