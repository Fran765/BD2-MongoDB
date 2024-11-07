package ar.unrn.blog.utils;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

@FunctionalInterface
public interface MongoOperation {
    void execute(MongoCollection<Document> collection);
}
