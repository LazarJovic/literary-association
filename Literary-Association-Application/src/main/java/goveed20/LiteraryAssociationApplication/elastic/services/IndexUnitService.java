package goveed20.LiteraryAssociationApplication.elastic.services;

import goveed20.LiteraryAssociationApplication.elastic.repositories.BetaReaderIndexUnitRepository;
import goveed20.LiteraryAssociationApplication.elastic.repositories.BookIndexUnitRepository;
import goveed20.LiteraryAssociationApplication.elastic.units.BetaReaderIndexUnit;
import goveed20.LiteraryAssociationApplication.elastic.units.BookIndexUnit;
import goveed20.LiteraryAssociationApplication.model.Book;
import goveed20.LiteraryAssociationApplication.model.Reader;
import goveed20.LiteraryAssociationApplication.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.stream.Collectors;

@Service
public class IndexUnitService {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private BookIndexUnitRepository bookIndexUnitRepository;

    @Autowired
    private BetaReaderIndexUnitRepository betaReaderIndexUnitRepository;

    public void saveBookIndexUnit(Book book) {
        BookIndexUnit bookIndexUnit = BookIndexUnit.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genre(book.getGenre().getGenre().serbianName)
                .isFree(book.getPrice() == 0)
                .writers(book.getAdditionalAuthors())
                .basicInfo(createBasicInfo(book))
                .synopsis(book.getSynopsis())
                .text(pdfService.getContentText(new File(book.getFile())))
                .build();

        bookIndexUnitRepository.save(bookIndexUnit);
    }

    private String createBasicInfo(Book book) {
        return String.format("%s, %s, %s, %s", book.getAuthors(), book.getPublicationYear(),
                book.getPublisher(), book.getPublicationPlace());
    }

    public void saveBetaReaderIndexUnit(Reader reader) {
        BetaReaderIndexUnit betaReaderIndexUnit = BetaReaderIndexUnit.builder()
                .id(reader.getId())
                .geoPoint(new GeoPoint(reader.getLocation().getLatitude(), reader.getLocation().getLongitude()))
                .name(String.format("%s %s", reader.getName(), reader.getSurname()))
                .genres(reader.getBetaReaderStatus().getBetaGenres().stream().map(genre ->
                        genre.getGenre().serbianName).collect(Collectors.toList()))
                .username(reader.getUsername())
                .build();

        betaReaderIndexUnitRepository.save(betaReaderIndexUnit);
    }

    public void clearAllIndexes() {
        bookIndexUnitRepository.deleteAll();
        betaReaderIndexUnitRepository.deleteAll();
    }

}
