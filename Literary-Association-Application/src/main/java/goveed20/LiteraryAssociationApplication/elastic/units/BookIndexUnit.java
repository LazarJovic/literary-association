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
@Document(indexName = "books", shards = 1, replicas = 0, type = "book")
public class BookIndexUnit {

    @Id
    @Field(type = FieldType.Long, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private Long id;

    @Field(type = FieldType.Text, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private String title;

    @Field(type = FieldType.Keyword)
    private String genre;

    @Field(type = FieldType.Boolean)
    private boolean isFree;

    @Field(type = FieldType.Text, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private String writers;

    @Field(type = FieldType.Text)
    private String basicInfo;

    @Field(type = FieldType.Text, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private String text;

}
