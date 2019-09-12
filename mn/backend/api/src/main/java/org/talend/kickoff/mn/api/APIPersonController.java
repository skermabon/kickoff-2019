package org.talend.kickoff.mn.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Put;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.talend.kickoff.mn.common.Person;

import java.util.List;

@Controller("/api/v1/persons/")
public class APIPersonController {

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to Person service
    public HttpResponse<Single<List<Person>>> list() {
        return HttpResponse.ok();
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to Person service
    public HttpResponse<Maybe<Person>> get(@PathVariable String id) {
        return HttpResponse.ok();
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to Person service
    public HttpResponse<Single<Person>> put(@PathVariable String id, @Body Person Person) {
        return HttpResponse.ok();
    }
}
