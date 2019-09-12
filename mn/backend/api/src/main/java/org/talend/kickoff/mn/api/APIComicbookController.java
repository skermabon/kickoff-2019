package org.talend.kickoff.mn.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.talend.kickoff.mn.common.Comicbook;
import org.talend.kickoff.mn.common.Person;

import java.util.List;

@Controller("/api/v1/comicbooks/")
public class APIComicbookController {

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to comicbook service
    public HttpResponse<Single<List<Comicbook>>> list() {
        return HttpResponse.ok();
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to comicbook service
    public HttpResponse<Maybe<Comicbook>> get(@PathVariable String id) {
        return HttpResponse.ok();
    }

    @Post(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to comicbook service
    public HttpResponse<Single<Comicbook>> post(@Body Comicbook comicbook) {
        return HttpResponse.ok();
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to comicbook service
    public HttpResponse<Single<Comicbook>> put(@PathVariable String id, @Body Comicbook comicbook) {
        return HttpResponse.ok();
    }

    @Delete(value = "/{id}")
    // TODO Redirect to comicbook service
    public HttpResponse<Single<Comicbook>> delete(@PathVariable String id) {
        return HttpResponse.ok();
    }

    @Get(value = "/{id}/persons", produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to comicbook service
    public HttpResponse<Single<List<Person>>> getPerson(@PathVariable String id) {
        return HttpResponse.ok();
    }

    @Post(value = "/{id}/persons", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to comicbook service
    public HttpResponse<Single<Comicbook>> postPerson(@Body Person person) {
        return HttpResponse.ok();
    }
}
