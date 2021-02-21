package goveed20.LiteraryAssociationApplication.elastic.utils;

import goveed20.LiteraryAssociationApplication.elastic.units.BookIndexUnit;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchHitsResultMapper implements SearchResultMapper {

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

        List<BookIndexUnit> resultUnits = new ArrayList<>();
        SearchHit[] hits = searchResponse.getHits().getHits();
        int length = hits.length;
        for (SearchHit hit : searchResponse.getHits()) {
            if (searchResponse.getHits().getHits().length <= 0) {
                return null;
            }

            Map<String, Object> source = hit.getSourceAsMap();
            String highlightText = String.format("...%s...", hit.getHighlightFields().get("text").getFragments()[0].toString());

            BookIndexUnit bookIndexUnit = BookIndexUnit.builder()
                    .id((Long) source.get("id"))
                    .title((String) source.get("title"))
                    .basicInfo((String) source.get("basicInfo"))
                    .isFree((boolean) source.get("isFree"))
                    .text(highlightText)
                    .build();

            resultUnits.add(bookIndexUnit);
        }

        if (resultUnits.size() > 0) {
            return new AggregatedPageImpl(resultUnits, pageable, searchResponse.getHits().getTotalHits(),
                    searchResponse.getAggregations(), searchResponse.getScrollId(),
                    searchResponse.getHits().getMaxScore());
        }

        return null;
    }
}
