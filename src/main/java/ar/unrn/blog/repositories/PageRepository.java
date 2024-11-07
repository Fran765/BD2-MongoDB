package ar.unrn.blog.repositories;

import ar.unrn.blog.utils.MongoService;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Repository

public class PageRepository {

    private static final String COLLECTION_NAME = "pages";

    @Autowired
    private MongoService mongoService;

    public Document getPageById(String id) {
        AtomicReference<Document> pageDoc = new AtomicReference<>();

        this.mongoService.executeWithMongo(COLLECTION_NAME, collection -> {

            Document document = collection.find(Filters.eq("_id", new ObjectId(id))).first();

            /*if (document != null) {
                document.put("_id", new Document("$oid", document.getObjectId("_id").toHexString()));
                document.put("date", new Document("$date", document.getDate("date")));
            }*/

            pageDoc.set(document);

        });

        return pageDoc.get();
    }

    public void createPage(String title, String text, String author, Date date){

        this.mongoService.executeWithMongo(COLLECTION_NAME, collection -> {

            Document newPage = new Document()
                    .append("title", title)
                    .append("text", text)
                    .append("author", author)
                    .append("date", date);

            collection.insertOne(newPage);
        });
    }

}
