package org.talend.kickoff.mn.comicbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.talend.kickoff.mn.common.Comicbook;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.talend.kickoff.mn.common.Person;
import org.talend.kickoff.mn.common.Role;

@MicronautTest public class ComicbookControllerTest {

    @Inject EmbeddedServer server;

    @Inject
    @Client("/comicbook/v1/comicbooks")
    HttpClient client;

    @BeforeAll static void setup() throws Exception {
    }

    @Test void emptyList() throws Exception {
        String retrieve = client.toBlocking().retrieve(HttpRequest.GET(""), String.class);
        assertNotNull(retrieve);
        assertEquals("[]", retrieve);
    }

    @Test void crud() throws Exception {
        // create
        Comicbook comicbook = new Comicbook();
        comicbook.setName("Action Comics");
        Comicbook retrieve = client.toBlocking().retrieve(HttpRequest.POST("/", comicbook), Comicbook.class);

        assertNotNull(retrieve);
        assertNotNull(retrieve.getId());
        assertEquals(comicbook.getName(), retrieve.getName());

        // read
        String id = retrieve.getId();
        retrieve = client.toBlocking().retrieve(HttpRequest.GET("/" + id), Comicbook.class);
        assertComicbookEquals(comicbook, retrieve, id);

        // read list
        List<Comicbook> retrieves = client.toBlocking().retrieve(HttpRequest.GET("/"), Argument.of(List.class, Comicbook.class));
        assertEquals(1, retrieves.size());
        retrieve = retrieves.get(0);
        assertComicbookEquals(comicbook, retrieve, id);

        // Update
        comicbook.setName("Detective Comics");
        comicbook.setId(id);
        retrieve = client.toBlocking().retrieve(HttpRequest.PUT("/" + id, comicbook), Comicbook.class);
        assertComicbookEquals(comicbook, retrieve, id);

        // Read again
        // read
        retrieve = client.toBlocking().retrieve(HttpRequest.GET("/" + id), Comicbook.class);
        assertComicbookEquals(comicbook, retrieve, id);

        // delete
        client.retrieve(HttpRequest.DELETE("/" + id));
        retrieve = client.toBlocking().retrieve(HttpRequest.GET("/" + id), Comicbook.class);
        assertComicbookEquals(comicbook, retrieve, id);
    }

    @Test
    public void testPerson() throws Exception {
        // create
        Comicbook comicbook = new Comicbook();
        comicbook.setName("Action Comics");
        Person jerry = new Person();
        jerry.setFirstname("Jerry");
        jerry.setLastname("Siegel");
        jerry.setId("jerry-id");

        Person joe = new Person();
        joe.setFirstname("Joe");
        joe.setLastname("Shuster");
        joe.setId("joe-id");

        comicbook.addPerson(Role.WRITER, jerry);
        comicbook.addPerson(Role.ARTIST, joe);
        Comicbook retrieve = client.toBlocking().retrieve(HttpRequest.POST("/", comicbook), Comicbook.class);

        Comicbook result = client.toBlocking().retrieve(HttpRequest.GET(""), Comicbook.class);
        assertNotNull(result);
        assertEquals(jerry.getFirstname(), result.getPersons().get(Role.WRITER.toString()).get(0).getFirstname());
    }

    private void assertComicbookEquals(Comicbook comicbook, Comicbook retrieve, String id) {
        assertNotNull(retrieve);
        assertEquals(id, retrieve.getId());
        assertEquals(comicbook.getName(), retrieve.getName());
    }

}