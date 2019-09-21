package org.talend.kickoff.mn.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
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
import org.talend.kickoff.mn.common.ComicbookClient;
import org.talend.kickoff.mn.common.Person;

import java.util.List;

@Controller("/api/v1/comicbooks/")
public class APIComicbookController {

    private final ComicbookClient client;

    public APIComicbookController(ComicbookClient client) {
        this.client = client;
    }

    @Get(value = "/", produces = MediaType.APPLICATION_JSON) public HttpResponse<List<Comicbook>> list() {
        return client.list();
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Comicbook> get(@PathVariable String id) {
        return client.get(id);
    }

    @Post(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Comicbook> post(@Body Comicbook comicbook) {
        return client.post(comicbook);
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Comicbook> put(@PathVariable String id, @Body Comicbook comicbook) {
        return client.put(id, comicbook);
    }

    @Delete(value = "/{id}") public HttpResponse delete(@PathVariable String id) {
        return client.delete(id);
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
