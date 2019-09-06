package org.talend.dataprep.mn.folder;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.talend.dataprep.mn.common.Folder;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest public class FolderControllerTest {

    @Inject EmbeddedServer server;

    @Inject @Client("/folder/v1/folders") HttpClient client;

    @BeforeAll static void setup() throws Exception {
    }

    @Test void emptyList() throws Exception {
        String retrieve = client.toBlocking().retrieve(HttpRequest.GET(""), String.class);
        assertNotNull(retrieve);
        assertEquals("[]", retrieve);
    }

    @Test void crud() throws Exception {
        // create
        Folder folder = new Folder();
        folder.setName("folder");
        folder.setPath("/");
        Folder retrieve = client.toBlocking().retrieve(HttpRequest.POST("/", folder), Folder.class);

        assertNotNull(retrieve);
        assertNotNull(retrieve.getId());
        assertEquals(folder.getName(), retrieve.getName());
        assertEquals(folder.getPath(), retrieve.getPath());

        // read
        String id = retrieve.getId();
        retrieve = client.toBlocking().retrieve(HttpRequest.GET("/" + id), Folder.class);
        assertFolderEquals(folder, retrieve, id);

        // read list
        List<Folder> retrieves = client.toBlocking().retrieve(HttpRequest.GET("/"), Argument.of(List.class, Folder.class));
        assertEquals(1, retrieves.size());
        retrieve = retrieves.get(0);
        assertFolderEquals(folder, retrieve, id);

        // Update
        folder.setName("new folder");
        folder.setId(id);
        retrieve = client.toBlocking().retrieve(HttpRequest.PUT("/" + id, folder), Folder.class);
        assertFolderEquals(folder, retrieve, id);

        // Read again
        // read
        retrieve = client.toBlocking().retrieve(HttpRequest.GET("/" + id), Folder.class);
        assertFolderEquals(folder, retrieve, id);

        // delete
        client.retrieve(HttpRequest.DELETE("/" + id));
        retrieve = client.toBlocking().retrieve(HttpRequest.GET("/" + id), Folder.class);
        assertFolderEquals(folder, retrieve, id);
    }

    private void assertFolderEquals(Folder folder, Folder retrieve, String id) {
        assertNotNull(retrieve);
        assertEquals(id, retrieve.getId());
        assertEquals(folder.getName(), retrieve.getName());
        assertEquals(folder.getPath(), retrieve.getPath());
    }

}