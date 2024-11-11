package ar.unrn.blog.domain.model;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Getter
@Document(collection = "posts")
public class Post {

    private ObjectId id;
    private String title;  //Un título del post
    private String text; //El texto completo del post.
    private List<String> tags; //Palabras o frases cortas que definen la temática específica del post.
    private String resume; //resumen del post
    private List<String> relatedLinks; //URLs de sitios web que hablen sobre el mismo tema (o relacionados) del que habla el post.
    private String author; //El autor del post.
    private Date date; //La fecha de publicación del post en el blog.
}
