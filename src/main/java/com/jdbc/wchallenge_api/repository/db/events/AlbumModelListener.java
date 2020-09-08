package com.jdbc.wchallenge_api.repository.db.events;

import com.jdbc.wchallenge_api.model.Album;
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
public class AlbumModelListener extends AbstractMongoEventListener<Album> {

  private final SequenceGeneratorService sequenceGenerator;

  @Autowired
  public AlbumModelListener(SequenceGeneratorService sequenceGenerator) {
    this.sequenceGenerator = sequenceGenerator;
  }

  @Override
  public void onBeforeConvert(BeforeConvertEvent<Album> event) {
    if (event.getSource().getId() < 1) {
      event.getSource().setId(sequenceGenerator.generateSequence(Album.SEQUENCE_NAME));
    }
  }
}
