package org.talend.dataprep.mn.preparation;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.talend.dataprep.mn.common.Preparation;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest class PreparationControllerTest {

    @Inject EmbeddedServer server;

    @Inject @Client("/preparation/v1/preparations") HttpClient client;

    @Test void emptyList() throws Exception {
        String retrieve = client.toBlocking().retrieve(HttpRequest.GET(""), String.class);
        assertNotNull(retrieve);
        assertEquals("[]", retrieve);
    }

    @Test void crud() throws Exception {
        // create
        Preparation Preparation = new Preparation();
        Preparation.setName("Preparation");
        Preparation retrieve = client.toBlocking().retrieve(HttpRequest.POST("/", Preparation), Preparation.class);

        assertNotNull(retrieve);
        assertNotNull(retrieve.getId());
        assertEquals(Preparation.getName(), retrieve.getName());

        // read
        String id = retrieve.getId();
        retrieve = client.toBlocking().retrieve(HttpRequest.GET("/" + id), Preparation.class);
        assertPreparationEquals(Preparation, retrieve, id);

        // read list
        List<Preparation> retrieves = client.toBlocking().retrieve(HttpRequest.GET("/"), Argument.of(List.class, Preparation.class));
        assertEquals(1, retrieves.size());
        retrieve = retrieves.get(0);
        assertPreparationEquals(Preparation, retrieve, id);

        // Update
        Preparation.setName("new Preparation");
        Preparation.setId(id);
        retrieve = client.toBlocking().retrieve(HttpRequest.PUT("/" + id, Preparation), Preparation.class);
        assertPreparationEquals(Preparation, retrieve, id);

        // Read again
        // read
        retrieve = client.toBlocking().retrieve(HttpRequest.GET("/" + id), Preparation.class);
        assertPreparationEquals(Preparation, retrieve, id);

        // delete
        client.retrieve(HttpRequest.DELETE("/" + id));
        retrieve = client.toBlocking().retrieve(HttpRequest.GET("/" + id), Preparation.class);
        assertPreparationEquals(Preparation, retrieve, id);
    }

    private void assertPreparationEquals(Preparation Preparation, Preparation retrieve, String id) {
        assertNotNull(retrieve);
        assertEquals(id, retrieve.getId());
        assertEquals(Preparation.getName(), retrieve.getName());
    }
}