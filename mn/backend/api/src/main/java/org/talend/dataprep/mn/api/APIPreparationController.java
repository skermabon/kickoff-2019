package org.talend.dataprep.mn.api;

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
import org.talend.dataprep.mn.common.Preparation;

import java.util.List;

@Controller("/api/v1/preparation/")
public class APIPreparationController {

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to Preparation service
    public HttpResponse<Single<List<Preparation>>> list() {
        return HttpResponse.ok();
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to Preparation service
    public HttpResponse<Maybe<Preparation>> get(@PathVariable String id) {
        return HttpResponse.ok();
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to Preparation service
    public HttpResponse<Single<Preparation>> put(@PathVariable String id, @Body Preparation Preparation) {
        return HttpResponse.ok();
    }
}
