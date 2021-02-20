package goveed20.LiteraryAssociationApplication.elastic.repositories;

import goveed20.LiteraryAssociationApplication.elastic.units.BetaReaderIndexUnit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BetaReaderIndexUnitRepository extends ElasticsearchRepository<BetaReaderIndexUnit, Long> {
}
