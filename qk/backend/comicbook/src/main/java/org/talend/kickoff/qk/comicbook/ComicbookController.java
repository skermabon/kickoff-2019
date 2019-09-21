package org.talend.kickoff.qk.comicbook;

import org.talend.kickoff.qk.common.Comicbook;
import org.talend.kickoff.qk.common.ComicbookRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CompletionStage;

@Path("/comicbook/v1/comicbooks") @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
public class ComicbookController {

    @Inject ComicbookRepository comicbookRepository;

    @GET @Path("/") public CompletionStage<List<Comicbook>> getAll() {
        return comicbookRepository.list();
    }

}
