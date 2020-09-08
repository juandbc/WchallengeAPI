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
@Document(collection = "album_permission")
public class AlbumPermission implements Serializable {

  private static final long serialVersionUID = -6653137842926976395L;

  @Transient
  public static final String SEQUENCE_NAME = "album_sequence";

  @Id
  private int id;
  private int albumId;
  private int userId;
  private boolean write;
  private boolean read;

  public AlbumPermission() {
  }

  public AlbumPermission(int albumId, int userId, boolean write, boolean read) {
    this.albumId = albumId;
    this.userId = userId;
    this.write = write;
    this.read = read;
  }

  public AlbumPermission(int id, int albumId, int userId, boolean write, boolean read) {
    this.id = id;
    this.albumId = albumId;
    this.userId = userId;
    this.write = write;
    this.read = read;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAlbumId() {
    return albumId;
  }

  public void setAlbumId(int albumId) {
    this.albumId = albumId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public boolean isWrite() {
    return write;
  }

  public void setWrite(boolean write) {
    this.write = write;
  }

  public boolean isRead() {
    return read;
  }

  public void setRead(boolean read) {
    this.read = read;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AlbumPermission that = (AlbumPermission) o;
    return id == that.id &&
            albumId == that.albumId &&
            userId == that.userId &&
            write == that.write &&
            read == that.read;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, albumId, userId, write, read);
  }

  @Override
  public String toString() {
    return "AlbumPermission{" +
            "id=" + id +
            ", albumId=" + albumId +
            ", userId=" + userId +
            ", write=" + write +
            ", read=" + read +
            '}';
  }
}
