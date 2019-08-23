package org.talend.dataprep.mn.common;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

@Singleton public class FolderRepository {

    public static final String FOLDER_COLLECTION = "folder";

    private final MongoClient mongoClient;

    private final DataPreparationConfiguration configuration;

    public FolderRepository(MongoClient mongoClient, DataPreparationConfiguration configuration) {
        this.mongoClient = mongoClient;
        this.configuration = configuration;
    }

    public Single<List<Folder>> list() {
        return Flowable.fromPublisher(getCollection().find()).toList();
    }

    public Maybe<Folder> get(String id) {
        return Flowable.fromPublisher(getCollection().find(Filters.eq("_id", id)).limit(1)).firstElement();
    }

    public Single<Folder> create(Folder folder) {
        if (folder.getId() == null) {
            folder.setId(UUID.randomUUID().toString());
        }
        return Single.fromPublisher(getCollection().insertOne(folder)).map(success -> folder);
    }

    private MongoCollection<Folder> getCollection() {
        return mongoClient.getDatabase(configuration.getDatabaseName()).getCollection(FOLDER_COLLECTION, Folder.class);
    }
}
