package org.talend.kickoff.qk.comicbook;


import org.talend.kickoff.qk.common.ComicbookRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/comicbook/v1/comicbooks") public class ComicbookController {

    @Inject ComicbookRepository service;

    @GET @Produces(MediaType.TEXT_PLAIN) public String hello() {
        return "hello\n";
    }

    @GET @Produces(MediaType.TEXT_PLAIN) @Path("/greeting/{name}")
    public String greeting(@PathParam("name") String name) {
        return service.greeting(name);
    }
}
