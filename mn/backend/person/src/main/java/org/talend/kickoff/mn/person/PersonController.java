package org.talend.kickoff.mn.person;

import java.util.List;

import org.talend.kickoff.mn.common.Person;
import org.talend.kickoff.mn.common.PersonOperations;
import org.talend.kickoff.mn.common.PersonRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;

@Controller("/person/v1/persons")
public class PersonController implements PersonOperations {

    private PersonRepository personRepository;

    public PersonController(PersonRepository PersonRepository) {
        this.personRepository = PersonRepository;
    }

    @Override
    public HttpResponse<List<Person>> list() {
        return HttpResponse.ok(personRepository.list().blockingGet());
    }

    @Override
    public HttpResponse<Person> get(@PathVariable String id) {
        Person person = personRepository.get(id).blockingGet();
        if (person != null) {
            return HttpResponse.ok(person);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Override
    public HttpResponse<Person> post(@Body Person person) {
        return HttpResponse.created(personRepository.create(person).blockingGet());
    }

    @Override
    public HttpResponse<Person> put(@PathVariable String id, @Body Person person) {
        return HttpResponse.ok(personRepository.update(person).blockingGet());
    }

    @Override
    public HttpResponse delete(@PathVariable String id) {
        personRepository.delete(id);
        return HttpResponse.noContent();
    }
}
