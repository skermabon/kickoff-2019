package org.talend.kickoff.mn.comicbook;

import org.talend.kickoff.mn.common.Comicbook;
import org.talend.kickoff.mn.common.ComicbookRepository;
import org.talend.kickoff.mn.common.Person;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ComicbookService {

    private final ComicbookRepository comicbookRepository;

    public ComicbookService(ComicbookRepository comicbookRepository) {
        this.comicbookRepository = comicbookRepository;
    }

    public void updatePerson(Person updatedPerson) {
        List<Comicbook> needToUpdate = comicbookRepository.list()
                .blockingGet()
                .stream()
                .filter(comicBook -> comicBook.getAllPersons().contains(updatedPerson))
                .collect(Collectors.toList());

        needToUpdate.stream().flatMap(comicBook -> comicBook.getAllPersons().stream()).forEach(person -> {
            person.setLastname(updatedPerson.getLastname());
            person.setFirstname(updatedPerson.getFirstname());
        });

        needToUpdate.stream().forEach(comicbook -> comicbookRepository.update(comicbook).blockingGet());

    }
}
