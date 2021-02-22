package goveed20.LiteraryAssociationApplication.elastic.repositories;

import goveed20.LiteraryAssociationApplication.elastic.units.BookIndexUnit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookIndexUnitRepository extends ElasticsearchRepository<BookIndexUnit, Long> {

}
