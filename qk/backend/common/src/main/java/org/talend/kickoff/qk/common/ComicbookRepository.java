package org.talend.kickoff.qk.common;

import com.mongodb.client.model.Filters;
import io.quarkus.mongodb.ReactiveMongoClient;
import io.quarkus.mongodb.ReactiveMongoCollection;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

@Singleton public class ComicbookRepository {

    public static final String COMICBOOK_COLLECTION = "comicbook";

    @Inject ComicbookConfiguration configuration;

    @Inject ReactiveMongoClient mongoClient;

    public CompletionStage<List<Comicbook>> list() {
        return getCollection().find().toList().run();
    }

    public CompletionStage<Optional<Comicbook>> get(String id) {
        return getCollection().find(Filters.eq("_id", id)).limit(1).findFirst().run();
    }

    public CompletionStage<Optional<Comicbook>> create(Comicbook comicbook) {
        if (comicbook.getId() == null) {
            comicbook.setId(UUID.randomUUID().toString());
        }
        getCollection().insertOne(comicbook);
        return get(comicbook.getId());
    }

    public CompletionStage<Optional<Comicbook>> update(Comicbook comicbook) {
        getCollection().replaceOne(Filters.eq("_id", comicbook.getId()), comicbook);
        return get(comicbook.getId());
    }

    public void delete(String id) {
        getCollection().deleteMany(Filters.eq("_id", id));
    }

    private ReactiveMongoCollection<Comicbook> getCollection() {
        return mongoClient.getDatabase(configuration.getDatabaseName())
                .getCollection(COMICBOOK_COLLECTION, Comicbook.class);
    }
}
