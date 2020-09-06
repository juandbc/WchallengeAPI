package com.jdbc.wchallenge_api.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jdbc.wchallenge_api.model.Comment;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@JsonComponent
public class CommentSerializer extends StdSerializer<Comment> {

  private static final long serialVersionUID = 1120046831562996710L;

  public CommentSerializer() {
    this(null);
  }

  public CommentSerializer(Class<Comment> t) {
    super(t);
  }

  @Override
  public void serialize(Comment value, JsonGenerator gen, SerializerProvider provider)
          throws IOException {

    gen.writeStartObject();

    if (value.getId() != 0 && value.getName() != null) {
      gen.writeNumberField("id", value.getId());
      gen.writeStringField("name", value.getName());
      gen.writeStringField("email", value.getEmail());
      gen.writeStringField("body", value.getBody());
      gen.writeNumberField("postId", value.getPostId());
    }

    gen.writeEndObject();
  }
}
