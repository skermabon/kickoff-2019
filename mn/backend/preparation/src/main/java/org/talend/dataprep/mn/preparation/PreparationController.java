package org.talend.dataprep.mn.preparation;

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
import org.talend.dataprep.mn.common.Preparation;
import org.talend.dataprep.mn.common.PreparationRepository;

@Controller("/preparation/v1/preparations")
public class PreparationController {

    private PreparationRepository preparationRepository;

    public PreparationController(PreparationRepository PreparationRepository) {
        this.preparationRepository = PreparationRepository;
    }

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Flowable<Preparation>> list() {
        return HttpResponse.ok(preparationRepository.list());
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Maybe<Preparation>> get(@PathVariable String id) {
        return HttpResponse.ok(preparationRepository.get(id));
    }

    @Post(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Single<Preparation>> post(@Body Preparation preparation) {
        return HttpResponse.created(preparationRepository.create(preparation));
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Single<Preparation>> put(@PathVariable String id, @Body Preparation Preparation) {
        return HttpResponse.ok(preparationRepository.update(Preparation));
    }

    @Delete(value = "/{id}")
    public HttpResponse<Single<Preparation>> delete(@PathVariable String id) {
        preparationRepository.delete(id);
        return HttpResponse.ok();
    }
}
