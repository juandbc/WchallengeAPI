package com.jdbc.wchallenge_api.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jdbc.wchallenge_api.model.Album;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@JsonComponent
public class AlbumSerializer extends StdSerializer<Album> {

  private static final long serialVersionUID = 7315855149607524666L;

  public AlbumSerializer() {
    this(null);
  }

  public AlbumSerializer(Class<Album> t) {
    super(t);
  }

  @Override
  public void serialize(Album value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject();

    if (value.getId() != 0 && value.getTitle() != null) {
      gen.writeNumberField("id", value.getId());
      gen.writeStringField("title", value.getTitle());
      gen.writeNumberField("userId", value.getUserId());
    }

    gen.writeEndObject();
  }
}
