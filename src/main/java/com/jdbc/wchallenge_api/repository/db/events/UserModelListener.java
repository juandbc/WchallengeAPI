package com.jdbc.wchallenge_api.repository.db.events;

import com.jdbc.wchallenge_api.model.User;
import com.jdbc.wchallenge_api.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Component
public class UserModelListener extends AbstractMongoEventListener<User> {

  private final SequenceGeneratorService sequenceGenerator;

  @Autowired
  public UserModelListener(SequenceGeneratorService sequenceGenerator) {
    this.sequenceGenerator = sequenceGenerator;
  }

  @Override
  public void onBeforeConvert(BeforeConvertEvent<User> event) {
    if (event.getSource().getId() < 1) {
      event.getSource().setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
    }
  }
}
