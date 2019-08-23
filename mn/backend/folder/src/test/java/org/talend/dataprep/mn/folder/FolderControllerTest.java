package org.talend.dataprep.mn.folder;

import com.mongodb.reactivestreams.client.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static de.flapdoodle.embed.process.runtime.Network.*;

@MicronautTest class FolderControllerTest {

    @Inject EmbeddedServer server;

    @Inject MongoClient mongoClient;

    @Inject @Client("/") HttpClient client;

    @BeforeAll static void setup() throws Exception {
    }

    @Test void list() throws Exception {
        client.toBlocking().retrieve(HttpRequest.GET("/folder/v1/folders"));
    }

    @Test void get() throws Exception {
    }

    @Test void post() throws Exception {

    }
}