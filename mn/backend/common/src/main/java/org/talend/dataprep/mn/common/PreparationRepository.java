package org.talend.dataprep.mn.common;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton public class PreparationRepository {

    public static final String PREPARATION_COLLECTION = "preparation";

    private final MongoClient mongoClient;

    private final DataPreparationConfiguration configuration;

    public PreparationRepository(MongoClient mongoClient, DataPreparationConfiguration configuration) {
        this.mongoClient = mongoClient;
        this.configuration = configuration;
    }

    public Flowable<Preparation> list() {
        return Flowable.fromPublisher(getCollection().find());
    }

    public Maybe<Preparation> get(String id) {
        return Flowable.fromPublisher(getCollection().find(Filters.eq("_id", id)).limit(1)).firstElement();
    }

    public Single<Preparation> create(Preparation Preparation) {
        if (Preparation.getId() == null) {
            Preparation.setId(UUID.randomUUID().toString());
        }
        return Single.fromPublisher(getCollection().insertOne(Preparation)).map(success -> Preparation);
    }

    public Single<Preparation> update(Preparation Preparation) {
        return Single.fromPublisher(getCollection().replaceOne(Filters.eq("_id", Preparation.getId()), Preparation)).map(success -> Preparation);
    }

    public void delete(String id) {
        getCollection().deleteMany(Filters.eq("_id", id));
    }

    private MongoCollection<Preparation> getCollection() {
        return mongoClient.getDatabase(configuration.getDatabaseName()).getCollection(PREPARATION_COLLECTION, Preparation.class);
    }
}
