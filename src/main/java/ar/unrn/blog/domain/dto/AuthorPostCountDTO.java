package ar.unrn.blog.domain.dto;

import lombok.Builder;
import lombok.Getter;
import org.bson.Document;

@Builder
@Getter
public class AuthorPostCountDTO {
    private String _id;
    private int count;

    public static AuthorPostCountDTO fromDocument(Document doc){
        return AuthorPostCountDTO.builder()
                ._id(doc.getString("_id"))
                .count(doc.getInteger("count"))
                .build();
    }
}
