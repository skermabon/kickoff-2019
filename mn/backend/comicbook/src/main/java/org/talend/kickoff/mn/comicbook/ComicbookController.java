package org.talend.kickoff.mn.comicbook;

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
import org.talend.kickoff.mn.common.ComicbookRepository;

import java.util.List;

@Controller("/comicbook/v1/comicbooks")
public class ComicbookController {

    private ComicbookRepository comicbookRepository;

    public ComicbookController(ComicbookRepository comicbookRepository) {
        this.comicbookRepository = comicbookRepository;
    }

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Single<List<Comicbook>>> list() {
        return HttpResponse.ok(comicbookRepository.list());
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Maybe<Comicbook>> get(@PathVariable String id) {
        return HttpResponse.ok(comicbookRepository.get(id));
    }

    @Post(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Single<Comicbook>> post(@Body Comicbook person) {
        return HttpResponse.created(comicbookRepository.create(person));
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Single<Comicbook>> put(@PathVariable String id, @Body Comicbook comicbook) {
        return HttpResponse.ok(comicbookRepository.update(comicbook));
    }

    @Delete(value = "/{id}")
    public HttpResponse<Single<Comicbook>> delete(@PathVariable String id) {
        comicbookRepository.delete(id);
        return HttpResponse.ok();
    }
}

