package org.talend.dataprep.mn.folder;

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
import org.talend.dataprep.mn.common.Folder;
import org.talend.dataprep.mn.common.FolderRepository;

import java.util.List;

@Controller("/folder/v1/folders")
public class FolderController {

    private FolderRepository folderRepository;

    public FolderController(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Single<List<Folder>>> list() {
        return HttpResponse.ok(folderRepository.list());
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Maybe<Folder>> get(@PathVariable String id) {
        return HttpResponse.ok(folderRepository.get(id));
    }

    @Post(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Single<Folder>> post(@Body Folder person) {
        return HttpResponse.created(folderRepository.create(person));
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Single<Folder>> put(@PathVariable String id, @Body Folder folder) {
        return HttpResponse.ok(folderRepository.update(folder));
    }

    @Delete(value = "/{id}")
    public HttpResponse<Single<Folder>> delete(@PathVariable String id) {
        folderRepository.delete(id);
        return HttpResponse.ok();
    }
}

