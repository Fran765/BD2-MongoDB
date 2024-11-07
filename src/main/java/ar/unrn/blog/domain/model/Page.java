package ar.unrn.blog.domain.model;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Getter
@Document(collection = "pages")
public class Page {

    private ObjectId id;
    private String title; //Un título de la página.
    private String text; //: El texto completo de la página.
    private String author; //El autor de la página.
    private Date date; // La fecha de publicación de la página en el blog.
}
