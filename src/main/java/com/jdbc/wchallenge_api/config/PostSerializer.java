package com.jdbc.wchallenge_api.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jdbc.wchallenge_api.model.Post;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@JsonComponent
public class PostSerializer extends StdSerializer<Post> {

  private static final long serialVersionUID = -3824819587968339937L;

  public PostSerializer() {
    this(null);
  }

  public PostSerializer(Class<Post> t) {
    super(t);
  }

  @Override
  public void serialize(Post value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject();

    if (value.getId() != 0 && value.getTitle() != null) {
      gen.writeNumberField("id", value.getId());
      gen.writeStringField("title", value.getTitle());
      gen.writeStringField("body", value.getBody());
      gen.writeNumberField("userId", value.getUserId());
    }

    gen.writeEndObject();
  }
}
