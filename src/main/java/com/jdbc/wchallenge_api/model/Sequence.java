package com.jdbc.wchallenge_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Document("sequence")
public class Sequence {

  @Id
  private String id;
  private Integer seq;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getSeq() {
    return seq;
  }

  public void setSeq(Integer seq) {
    this.seq = seq;
  }
}
