package ar.unrn.blog.repositories;

import ar.unrn.blog.utils.MongoService;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class PostRepository {

    private static final String COLLECTION_NAME = "posts";

    @Autowired
    private MongoService mongoService;

    public Document findPostById(String id){
        AtomicReference<Document> postDoc  = new AtomicReference<>();

        this.mongoService.executeWithMongo(COLLECTION_NAME, collection -> {

            Document document = collection.find(Filters.eq("_id", new ObjectId(id))).first();

            postDoc.set(document);
        });

        return postDoc.get();
    }
    public List<Document> findLatestPosts(){

        List<Document> result = new ArrayList<>();

        this.mongoService.executeWithMongo(COLLECTION_NAME, collection -> {

            Bson projectionFields = Projections.fields(
                    Projections.include("id", "title", "resume"));

            collection.find()
                    .projection(projectionFields)
                    .sort(Sorts.descending("date"))
                    .limit(4)
                    .into(result);
        });

        return result;
    }

    public List<Document> findPostsByAuthorName(String name){

        List<Document> result = new ArrayList<>();

        this.mongoService.executeWithMongo(COLLECTION_NAME, collection -> {

            collection.find(Filters.eq("author", name)).into(result);

        });

        return result;
    }

    public List<Document> findPostByText(String text){

        List<Document> result = new ArrayList<>();

        this.mongoService.executeWithMongo(COLLECTION_NAME, collection -> {
            /*
            * TODO: ¿Debo crear un indice por campo del post o solo del text?
            *  */
            collection.createIndex(Indexes.text("text"));

            collection.find(Filters.text(text)).into(result);
        });

        return result;

    }

    public List<Document> findAuthorsAndNumberOfPost(){

        List<Document> docAuthorsAndNumberOfPost = new ArrayList<>();

        this.mongoService.executeWithMongo(COLLECTION_NAME, collection -> {

            collection.aggregate(List.of(Aggregates.group("$author",
                    Accumulators.sum("count",
                            1))))
                    .into(docAuthorsAndNumberOfPost);

        });

        return docAuthorsAndNumberOfPost;
    }

    public void createPost(String title,
                           String text,
                           List<String> tag,
                           String resume,
                           List<String> relatedLinks,
                           String author,
                           Date date) {

        this.mongoService.executeWithMongo(COLLECTION_NAME, collection -> {

            Document newPost = new Document()
                    .append("title", title)
                    .append("text", text)
                    .append("tag", tag)
                    .append("resume", resume)
                    .append("relatedLinks", relatedLinks)
                    .append("author", author)
                    .append("date", date);

            collection.insertOne(newPost);
        });
    }

}
