package ar.unrn.blog.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
public class MongoService {

    private static final String DATABASE_NAME = "BlogDB";

    public void executeWithMongo(String collectionName, MongoOperation operation) {

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(collectionName);
            operation.execute(collection);
        }
    }

}
