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
import org.talend.dataprep.mn.common.Folder;
import org.talend.dataprep.mn.common.Preparation;

import java.util.List;

@Controller("/api/v1/folders/")
public class APIFolderController {

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to folder service
    public HttpResponse<Single<List<Folder>>> list() {
        return HttpResponse.ok();
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to folder service
    public HttpResponse<Maybe<Folder>> get(@PathVariable String id) {
        return HttpResponse.ok();
    }

    @Post(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to folder service
    public HttpResponse<Single<Folder>> post(@Body Folder folder) {
        return HttpResponse.ok();
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to folder service
    public HttpResponse<Single<Folder>> put(@PathVariable String id, @Body Folder folder) {
        return HttpResponse.ok();
    }

    @Delete(value = "/{id}")
    // TODO Redirect to folder service
    public HttpResponse<Single<Folder>> delete(@PathVariable String id) {
        return HttpResponse.ok();
    }

    @Get(value = "/{id}/preparations", produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to folder service
    public HttpResponse<Single<List<Preparation>>> getPreparation(@PathVariable String id) {
        return HttpResponse.ok();
    }

    @Post(value = "/{id}/preparation", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to folder service
    public HttpResponse<Single<Folder>> postPreparation(@Body Preparation preparation) {
        return HttpResponse.ok();
    }

    @Put(value = "/{id}/preparation/{preparationId}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    // TODO Redirect to folder service
    public HttpResponse<Single<Folder>> move(@PathVariable String id, @PathVariable String preparationId, @Body String newFolderId) {
        return HttpResponse.ok();
    }

}
