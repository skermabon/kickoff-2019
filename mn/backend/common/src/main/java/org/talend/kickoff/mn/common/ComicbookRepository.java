package org.talend.kickoff.mn.common;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ComicbookRepository {

    public static final String COMICBOOK_COLLECTION = "comicbook";

    private final MongoClient mongoClient;

    public ComicbookRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Single<List<Comicbook>> list() {
        return Flowable.fromPublisher(getCollection().find()).toList();
    }

    private MongoCollection<Comicbook> getCollection() {
        return mongoClient
                .getDatabase("kickoff")
                .getCollection(COMICBOOK_COLLECTION, Comicbook.class);
    }
}
