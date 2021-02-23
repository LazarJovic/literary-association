package goveed20.LiteraryAssociationApplication.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AdditionalContentDTO {
    private boolean isList;
    private Object content;
    private boolean isPlagiarism;
}
