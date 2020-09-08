package com.jdbc.wchallenge_api.repository.db.events;

import com.jdbc.wchallenge_api.model.Photo;
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
public class PhotoModelListener extends AbstractMongoEventListener<Photo> {

  private final SequenceGeneratorService sequenceGenerator;

  @Autowired
  public PhotoModelListener(SequenceGeneratorService sequenceGenerator) {
    this.sequenceGenerator = sequenceGenerator;
  }

  @Override
  public void onBeforeConvert(BeforeConvertEvent<Photo> event) {
    if (event.getSource().getId() < 1) {
      event.getSource().setId(sequenceGenerator.generateSequence(Photo.SEQUENCE_NAME));
    }
  }
}
