package goveed20.LiteraryAssociationApplication.elastic.services;

import goveed20.LiteraryAssociationApplication.elastic.repositories.BookIndexUnitRepository;
import goveed20.LiteraryAssociationApplication.elastic.units.BookIndexUnit;
import goveed20.LiteraryAssociationApplication.model.Book;
import goveed20.LiteraryAssociationApplication.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class IndexUnitService {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private BookIndexUnitRepository bookIndexUnitRepository;

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

}
