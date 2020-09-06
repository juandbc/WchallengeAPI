package com.jdbc.wchallenge_api.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jdbc.wchallenge_api.model.User;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@JsonComponent
public class UserSerializer extends StdSerializer<User> {

  private static final long serialVersionUID = -4820552289732286443L;

  public UserSerializer() {
    this(null);
  }

  public UserSerializer(Class<User> t) {
    super(t);
  }

  @Override
  public void serialize(User value, JsonGenerator gen, SerializerProvider provider)
          throws IOException {

    gen.writeStartObject();

    if (value.getId() != 0 && value.getName() != null) {
      gen.writeNumberField("id", value.getId());
      gen.writeStringField("name", value.getName());
      gen.writeStringField("username", value.getUsername());
      gen.writeStringField("email", value.getEmail());
      gen.writeObjectField("address", value.getAddress());
      gen.writeStringField("phone", value.getPhone());
      gen.writeStringField("website", value.getWebsite());
      gen.writeObjectField("company", value.getCompany());
    }

    gen.writeEndObject();
  }
}
