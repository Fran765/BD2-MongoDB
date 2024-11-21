package ar.unrn.blog.config;

import ar.unrn.blog.utils.MongoService;
import com.mongodb.client.model.Indexes;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class MongoInitializer {

    @Autowired
    private MongoService mongoService;
    private static final String COLLECTION_NAME = "posts";

    @PostConstruct
    private void initializeIndexes() {
        mongoService.executeWithMongo(COLLECTION_NAME, collection -> {

            boolean indexExists = collection.listIndexes()
                    .into(new ArrayList<>())
                    .stream()
                    .anyMatch(index -> index.get("name").equals("text"));

            if (!indexExists)
                collection.createIndex(Indexes.text("text"));
        });
    }
}
