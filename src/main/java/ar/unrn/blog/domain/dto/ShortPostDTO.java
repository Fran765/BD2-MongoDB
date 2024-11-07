package ar.unrn.blog.domain.dto;

import lombok.Builder;
import lombok.Getter;
import org.bson.Document;

@Builder
@Getter
public class ShortPostDTO {

    private Document id;
    private String title;
    private String resume;

    public static ShortPostDTO fromDocument(Document docPost){

        Document id = new Document("$oid", docPost.getObjectId("_id").toHexString());

        return ShortPostDTO.builder()
                .id(id)
                .title(docPost.getString("title"))
                .resume(docPost.getString("resume"))
                .build();
    }
}
