package goveed20.LiteraryAssociationApplication.elastic.units;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(indexName = "books", replicas = 0, type = "book")
public class BookIndexUnit {

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private String title;

    @Field(type = FieldType.Keyword)
    private String genre;

    @Field(type = FieldType.Boolean)
    private Boolean isFree;

    @Field(type = FieldType.Text, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private String writers;

    @Field(type = FieldType.Text)
    private String basicInfo;

    @Field(type = FieldType.Text)
    private String synopsis;

    @Field(type = FieldType.Text, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private String text;

}
