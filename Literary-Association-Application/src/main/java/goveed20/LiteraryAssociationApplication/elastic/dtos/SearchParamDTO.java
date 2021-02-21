package goveed20.LiteraryAssociationApplication.elastic.dtos;

import goveed20.LiteraryAssociationApplication.elastic.utils.BooleanParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchParamDTO {

    private String name;

    private String value;

    private Boolean isPhraze;

    private BooleanParam booleanParam;

}
