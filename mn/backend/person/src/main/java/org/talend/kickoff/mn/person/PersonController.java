package org.talend.kickoff.mn.person;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.talend.kickoff.mn.common.Person;
import org.talend.kickoff.mn.common.PersonRepository;

@Controller("/person/v1/persons")
public class PersonController {

    private PersonRepository personRepository;

    public PersonController(PersonRepository PersonRepository) {
        this.personRepository = PersonRepository;
    }

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Flowable<Person>> list() {
        return HttpResponse.ok(personRepository.list());
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Maybe<Person>> get(@PathVariable String id) {
        return HttpResponse.ok(personRepository.get(id));
    }

    @Post(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Single<Person>> post(@Body Person person) {
        return HttpResponse.created(personRepository.create(person));
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Single<Person>> put(@PathVariable String id, @Body Person Person) {
        return HttpResponse.ok(personRepository.update(Person));
    }

    @Delete(value = "/{id}")
    public HttpResponse<Single<Person>> delete(@PathVariable String id) {
        personRepository.delete(id);
        return HttpResponse.ok();
    }
}
