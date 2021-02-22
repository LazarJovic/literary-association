package goveed20.LiteraryAssociationApplication.elastic.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchQueryDTO {

    private List<SearchParamDTO> searchParams;

    private Integer pageNum;

    private boolean allBooks;

}
