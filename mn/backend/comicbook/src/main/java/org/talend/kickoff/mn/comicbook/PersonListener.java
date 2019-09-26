package org.talend.kickoff.mn.comicbook;

import org.talend.kickoff.mn.common.Person;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class PersonListener {

    private final ComicbookService comicbookService;

    public PersonListener(ComicbookService comicbookService) {
        this.comicbookService = comicbookService;
    }

    @Topic("person")
    public void receivePerson(@KafkaKey String id, Person updatedPerson) {
        comicbookService.updatePerson(updatedPerson);
    }
}
