package ar.unrn.blog.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.bson.Document;

@Builder
@Getter
public class PageDTO {

    private Document _id;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @NotBlank
    private String author;
    private Document date;

    public static PageDTO fromDocument(Document docPage){

        Document id = new Document("$oid", docPage.getObjectId("_id").toHexString());
        Document date = new Document("$date", docPage.getDate("date"));

        return PageDTO.builder()
                ._id(id)
                .title(docPage.getString("title"))
                .text(docPage.getString("text"))
                .author(docPage.getString("author"))
                .date(date)
                .build();
    }
}
