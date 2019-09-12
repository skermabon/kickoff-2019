package org.talend.kickoff.qk.comicbook;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest class ComicbookControllerTest {

    @Test void emptyList() throws Exception {
        given().when().get("/comicbook/v1/comicbooks").then().statusCode(200).body(is("[]"));
    }
/*
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
        List<Comicbook> retrieves =
                client.toBlocking().retrieve(HttpRequest.GET("/"), Argument.of(List.class, Comicbook.class));
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

    private void assertComicbookEquals(Comicbook comicbook, Comicbook retrieve, String id) {
        assertNotNull(retrieve);
        assertEquals(id, retrieve.getId());
        assertEquals(comicbook.getName(), retrieve.getName());
    }

 */
}