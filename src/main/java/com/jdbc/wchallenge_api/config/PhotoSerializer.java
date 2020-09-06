package com.jdbc.wchallenge_api.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jdbc.wchallenge_api.model.Photo;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@JsonComponent
public class PhotoSerializer extends StdSerializer<Photo> {

  private static final long serialVersionUID = 6041422174905128728L;

  public PhotoSerializer() {
    this(null);
  }

  public PhotoSerializer(Class<Photo> t) {
    super(t);
  }

  @Override
  public void serialize(Photo value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject();

    if (value.getId() != 0 && value.getTitle() != null) {
      gen.writeNumberField("id", value.getId());
      gen.writeStringField("title", value.getTitle());
      gen.writeStringField("url", value.getUrl());
      gen.writeStringField("thumbnailUrl", value.getThumbnailUrl());
      gen.writeNumberField("albumId", value.getAlbumId());
    }

    gen.writeEndObject();
  }
}
