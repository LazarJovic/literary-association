package goveed20.LiteraryAssociationApplication.elastic.units;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(indexName = "beta-readers", shards = 1, replicas = 0, type = "beta-reader")
public class BetaReaderIndexUnit {

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @GeoPointField
    private GeoPoint geoPoint;

    @Field(type = FieldType.Text, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private String name;

    @Field(type = FieldType.Keyword)
    private List<String> genres;
}
