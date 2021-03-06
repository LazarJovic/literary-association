package goveed20.LiteraryAssociationApplication.utils;

import goveed20.LiteraryAssociationApplication.elastic.services.IndexUnitService;
import goveed20.LiteraryAssociationApplication.model.*;
import goveed20.LiteraryAssociationApplication.model.enums.GenreEnum;
import goveed20.LiteraryAssociationApplication.model.enums.TransactionStatus;
import goveed20.LiteraryAssociationApplication.model.enums.UserRole;
import goveed20.LiteraryAssociationApplication.repositories.BaseUserRepository;
import goveed20.LiteraryAssociationApplication.repositories.BookRepository;
import goveed20.LiteraryAssociationApplication.repositories.GenreRepository;
import goveed20.LiteraryAssociationApplication.repositories.RetailerRepository;
import goveed20.LiteraryAssociationApplication.services.CamundaUserService;
import goveed20.LiteraryAssociationApplication.services.LocationService;
import goveed20.LiteraryAssociationApplication.services.PlagiarismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DummyDataService {

    private static final String booksFolder = "E:/UDD/literary-association/Literary-Association-Application/src/main/resources/books/";

    @Autowired
    private IndexUnitService indexUnitService;

    @Autowired
    private BaseUserRepository baseUserRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CamundaUserService camundaUserService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private PlagiarismService plagiarismService;

    @Autowired
    private RetailerRepository retailerRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void addDummyData() throws IOException {
        indexUnitService.clearAllIndexes();

        Location noviSad = locationService.createLocation("Serbia", "Novi Sad");
        Location barcelona = locationService.createLocation("Spain", "Barcelona");
        Location paris = locationService.createLocation("France", "Paris");
        Location belgrade = locationService.createLocation("Serbia", "Belgrade");
        Location zagreb = locationService.createLocation("Croatia", "Zagreb");

        if (baseUserRepository.findAllByRole(UserRole.BOARD_MEMBER).isEmpty()) {
            BaseUser boardMember1 = BaseUser.builder()
                    .name("board_member_1_name")
                    .surname("board_member_1_surname")
                    .email("board_member_1@test.com")
                    .username("boardMember1")
                    .password(passwordEncoder.encode("board_member_1"))
                    .verified(true)
                    .role(UserRole.BOARD_MEMBER)
                    .location(noviSad.toBuilder().build())
                    .build();

            BaseUser boardMember2 = BaseUser.builder()
                    .name("board_member_2_name")
                    .surname("board_member_2_surname")
                    .email("board_member_2@test.com")
                    .username("boardMember2")
                    .verified(true)
                    .role(UserRole.BOARD_MEMBER)
                    .password(passwordEncoder.encode("board_member_2"))
                    .location(noviSad.toBuilder().build())
                    .build();

            baseUserRepository.save(boardMember1);
            baseUserRepository.save(boardMember2);

            camundaUserService.createCamundaUser(boardMember1);
            camundaUserService.createCamundaUser(boardMember2);
        }

        if (baseUserRepository.findAllByRole(UserRole.WRITER).isEmpty() && bookRepository.findAll().isEmpty() && retailerRepository.findAll().isEmpty()
        && baseUserRepository.findAllByRole(UserRole.READER).isEmpty()) {

            if (genreRepository.findAll().isEmpty()) {
                Arrays.stream(GenreEnum.values()).forEach(e -> genreRepository.save(new Genre(null, e)));
            }

            Genre g1 = genreRepository.findByGenre(GenreEnum.ADVENTURE);
            Genre g2 = genreRepository.findByGenre(GenreEnum.FANTASY);
            Genre g3 = genreRepository.findByGenre(GenreEnum.EROTIC);
            Genre g4 = genreRepository.findByGenre(GenreEnum.SCIENCE);

            Reader reader1 = Reader.readerBuilder()
                    .role(UserRole.READER)
                    .username("reader1")
                    .password(passwordEncoder.encode("password1"))
                    .name("reader1")
                    .surname("reader1")
                    .email("reader1@maildrop.cc")
                    .comments(new HashSet<>())
                    .transactions(new HashSet<>())
                    .genres(new HashSet<>())
                    .betaReader(false)
                    .location(noviSad.toBuilder().build())
                    .verified(true)
                    .build();

            Reader reader2 = Reader.readerBuilder()
                    .role(UserRole.READER)
                    .username("reader2")
                    .password(passwordEncoder.encode("password2"))
                    .name("reader2")
                    .surname("reader2")
                    .email("reader2@maildrop.cc")
                    .comments(new HashSet<>())
                    .transactions(new HashSet<>())
                    .genres(new HashSet<>())
                    .betaReader(false)
                    .location(noviSad.toBuilder().build())
                    .verified(true)
                    .build();

            BetaReaderStatus status2 = BetaReaderStatus.builder()
                    .betaGenres(Collections.singleton(g4))
                    .reader(reader2)
                    .build();

            reader2.setBetaReaderStatus(status2);

            Reader reader3 = Reader.readerBuilder()
                    .role(UserRole.READER)
                    .username("reader3")
                    .password(passwordEncoder.encode("password3"))
                    .name("reader3")
                    .surname("reader3")
                    .email("reader3@maildrop.cc")
                    .comments(new HashSet<>())
                    .transactions(new HashSet<>())
                    .genres(new HashSet<>())
                    .betaReader(true)
                    .location(zagreb.toBuilder().build())
                    .verified(true)
                    .build();

            BetaReaderStatus status3 = BetaReaderStatus.builder()
                    .reader(reader3)
                    .betaGenres(Stream.of(g4, g1).collect(Collectors.toSet()))
                    .build();

            reader3.setBetaReaderStatus(status3);

            Reader reader4 = Reader.readerBuilder()
                    .role(UserRole.READER)
                    .username("reader4")
                    .password(passwordEncoder.encode("password4"))
                    .name("reader4")
                    .surname("reader4")
                    .email("reader4@maildrop.cc")
                    .comments(new HashSet<>())
                    .transactions(new HashSet<>())
                    .genres(new HashSet<>())
                    .betaReader(true)
                    .location(barcelona.toBuilder().build())
                    .verified(true)
                    .build();

            BetaReaderStatus status4 = BetaReaderStatus.builder()
                    .reader(reader4)
                    .betaGenres(new HashSet<>(genreRepository.findAll()))
                    .build();

            reader4.setBetaReaderStatus(status4);

            Writer writer1 = Writer.writerBuilder()
                    .role(UserRole.WRITER)
                    .genres(new HashSet<>())
                    .location(noviSad.toBuilder().build())
                    .comments(new HashSet<>())
                    .transactions(new HashSet<>())
                    .verified(true)
                    .membershipApproved(true)
                    .workingPapers(new HashSet<>())
                    .books(new HashSet<>())
                    .username("perata")
                    .password(passwordEncoder.encode("Pera1997!"))
                    .name("Pero")
                    .surname("Peric")
                    .email("perata@maildrop.cc")
                    .genres(new HashSet<>())
                    .build();

            Writer writer2 = Writer.writerBuilder()
                    .role(UserRole.WRITER)
                    .genres(new HashSet<>())
                    .location(noviSad.toBuilder().build())
                    .comments(new HashSet<>())
                    .transactions(new HashSet<>())
                    .verified(true)
                    .membershipApproved(true)
                    .workingPapers(new HashSet<>())
                    .books(new HashSet<>())
                    .username("lazata")
                    .password(passwordEncoder.encode("Laza1997!"))
                    .name("Lazo")
                    .surname("Lazic")
                    .email("lazata@maildrop.cc")
                    .genres(new HashSet<>())
                    .build();

            Writer writer3 = Writer.writerBuilder()
                    .role(UserRole.WRITER)
                    .genres(new HashSet<>())
                    .location(noviSad.toBuilder().build())
                    .comments(new HashSet<>())
                    .transactions(new HashSet<>())
                    .verified(true)
                    .membershipApproved(true)
                    .workingPapers(new HashSet<>())
                    .books(new HashSet<>())
                    .username("radulata")
                    .password(passwordEncoder.encode("Radule1997!"))
                    .name("Radulo")
                    .surname("Radulic")
                    .email("radulo@maildrop.cc")
                    .genres(new HashSet<>())
                    .build();

            Writer writer4 = Writer.writerBuilder()
                    .role(UserRole.WRITER)
                    .genres(new HashSet<>())
                    .location(noviSad.toBuilder().build())
                    .comments(new HashSet<>())
                    .transactions(new HashSet<>())
                    .verified(true)
                    .membershipApproved(true)
                    .workingPapers(new HashSet<>())
                    .books(new HashSet<>())
                    .username("gagatata")
                    .password(passwordEncoder.encode("Gago1997!"))
                    .name("Gago")
                    .surname("Gagic")
                    .email("gago@maildrop.cc")
                    .genres(new HashSet<>())
                    .build();

            Writer writer5 = Writer.writerBuilder()
                    .role(UserRole.WRITER)
                    .genres(new HashSet<>())
                    .location(noviSad.toBuilder().build())
                    .comments(new HashSet<>())
                    .transactions(new HashSet<>())
                    .verified(true)
                    .membershipApproved(true)
                    .workingPapers(new HashSet<>())
                    .books(new HashSet<>())
                    .username("djolatata")
                    .password(passwordEncoder.encode("Djole1997!"))
                    .name("Djole")
                    .surname("Djolic")
                    .email("djole@maildrop.cc")
                    .genres(new HashSet<>())
                    .build();

            camundaUserService.createCamundaUser(writer1);
            camundaUserService.createCamundaUser(writer2);
            camundaUserService.createCamundaUser(writer3);
            camundaUserService.createCamundaUser(writer4);
            camundaUserService.createCamundaUser(writer5);

            Book b1 = Book.bookBuilder()
                    .file(String.format("%sUpravljanje digitalnim dokumentima.pdf", booksFolder))
                    .title("Upravljanje digitalnim dokumentima")
                    .synopsis("Knjiga vezana za upravljanje digitalnim dokumentima")
                    .genre(g1)
                    .ISBN("0-3818-9816-4")
                    .keywords("dokument,sistem,digitalno")
                    .publisher("FTN")
                    .publicationYear(2014)
                    .pages(240)
                    .publicationPlace("Novi Sad, Srbija")
                    .price(0.0)
                    .additionalAuthors("Branko Milosavljevic,Dragan Ivanovic")
                    .build();
            b1.setWriter(writer1);

            Book b2 = Book.bookBuilder()
                    .file(String.format("%sKrv vilenjaka.pdf", booksFolder))
                    .title("Krv vilenjaka")
                    .synopsis("Knjiga o Geraltu od Rivije")
                    .genre(g2)
                    .ISBN("0-8823-8460-0")
                    .keywords("vestac,dvorac,cudoviste")
                    .publisher("Carobna knjiga")
                    .publicationYear(2012)
                    .pages(317)
                    .price(16.0)
                    .publicationPlace("Beograd, Srbija")
                    .additionalAuthors("Andzej Sapkovski,Milica Markic")
                    .build();
            b2.setWriter(writer2);

            Book b3 = Book.bookBuilder()
                    .file(String.format("%sLiterarno udruzenje.pdf", booksFolder))
                    .title("Literarno udruzenje")
                    .synopsis("Specifikacija projekta iz tri predmeta")
                    .genre(g3)
                    .ISBN("0-6918-9816-4")
                    .keywords("projekat,udruzenje,specifikacija")
                    .publisher("FTN")
                    .publicationYear(2020)
                    .pages(690)
                    .publicationPlace("Novi Sad, Srbija")
                    .price(30.0)
                    .additionalAuthors("Zolata Zolka, Radata Rolka")
                    .build();
            b3.setWriter(writer3);

            Book book = Book.bookBuilder()
                    .file(String.format("%sUvod u modelovanje softvera.pdf", booksFolder))
                    .title("Uvod u modelovanje softvera")
                    .synopsis("Knjiga za modelovanje softvera")
                    .genre(g3)
                    .ISBN("0-6918-5657-4")
                    .keywords("softver,modelovanje,sablon")
                    .publisher("FTN")
                    .publicationYear(2020)
                    .pages(690)
                    .publicationPlace("Novi Sad, Srbija")
                    .price(30.0)
                    .additionalAuthors("Gordana Milosavljevic")
                    .build();
            book.setWriter(writer4);

            Book book2 = Book.bookBuilder()
                    .file(String.format("%sValidacija podataka.pdf", booksFolder))
                    .title("Validacija podataka")
                    .synopsis("Saveti za validaciju podataka")
                    .genre(g3)
                    .ISBN("0-6147-9843-4")
                    .keywords("validacija,podatak,pravilo")
                    .publisher("FTN")
                    .publicationYear(2020)
                    .pages(690)
                    .publicationPlace("Novi Sad, Srbija")
                    .price(30.0)
                    .additionalAuthors("Goran Sladic")
                    .build();
            book2.setWriter(writer5);

            bookRepository.save(b1);
            bookRepository.save(b2);
            bookRepository.save(b3);
            bookRepository.save(book);
            bookRepository.save(book2);

            indexUnitService.saveBookIndexUnit(b1);
            indexUnitService.saveBookIndexUnit(b2);
            indexUnitService.saveBookIndexUnit(b3);
            indexUnitService.saveBookIndexUnit(book);
            indexUnitService.saveBookIndexUnit(book2);

            plagiarismService.uploadPaper(b1.getTitle(), b1.getFile(), false);
            plagiarismService.uploadPaper(b2.getTitle(), b2.getFile(), false);
            plagiarismService.uploadPaper(b3.getTitle(), b3.getFile(), false);
            plagiarismService.uploadPaper(book.getTitle(), book.getFile(), false);
            plagiarismService.uploadPaper(book2.getTitle(), book2.getFile(), false);

            InvoiceItem item = InvoiceItem.builder().name(b3.getTitle())
                    .price(b3.getPrice()).quantity(1).build();
            Set<InvoiceItem> items = new HashSet<>();
            items.add(item);

            Retailer r = Retailer.builder()
                    .name("Laguna")
                    .email("laguna@maildrop.cc")
                    .books(new HashSet<>(Arrays.asList(b1, b2, b3, book, book2)))
                    .build();

            retailerRepository.save(r);

            Invoice invoice = Invoice.builder().retailer(r).invoiceItems(items).build();

            Transaction transaction = Transaction.builder().completedOn(new Date()).createdOn(new Date())
                    .initializedOn(new Date()).done(true).paidWith("bank-service").total(b3.getPrice()).invoice(invoice)
                    .status(TransactionStatus.COMPLETED).build();
            invoice.setTransaction(transaction);

            reader1.getTransactions().add(transaction);

            baseUserRepository.save(reader1);
            baseUserRepository.save(reader2);
            baseUserRepository.save(reader3);
            baseUserRepository.save(reader4);

            camundaUserService.createCamundaUser(reader1);
            camundaUserService.createCamundaUser(reader2);
            camundaUserService.createCamundaUser(reader3);
            camundaUserService.createCamundaUser(reader4);

            indexUnitService.saveBetaReaderIndexUnit(reader2);
            indexUnitService.saveBetaReaderIndexUnit(reader3);
            indexUnitService.saveBetaReaderIndexUnit(reader4);
        }


        if (baseUserRepository.findAllByRole(UserRole.EDITOR).isEmpty()) {
            BaseUser editor = BaseUser.builder()
                    .role(UserRole.EDITOR)
                    .username("editor")
                    .password(passwordEncoder.encode("Editor1997!"))
                    .email("editor@maildrop.cc")
                    .name("Editor")
                    .surname("Editorovic")
                    .verified(true)
                    .location(belgrade.toBuilder().build())
                    .build();

            BaseUser editor2 = BaseUser.builder()
                    .role(UserRole.EDITOR)
                    .username("mujoalen")
                    .password(passwordEncoder.encode("Editor1997!"))
                    .email("editor2@maildrop.cc")
                    .name("Mujo")
                    .surname("Alen")
                    .verified(true)
                    .location(noviSad.toBuilder().build())
                    .build();

            BaseUser editor3 = BaseUser.builder()
                    .role(UserRole.EDITOR)
                    .username("jurica")
                    .password(passwordEncoder.encode("Editor1997!"))
                    .email("editor3@maildrop.cc")
                    .name("Jurica")
                    .surname("Juric")
                    .verified(true)
                    .location(zagreb.toBuilder().build())
                    .build();

            BaseUser editor4 = BaseUser.builder()
                    .role(UserRole.EDITOR)
                    .username("peronikic")
                    .password(passwordEncoder.encode("Editor1997!"))
                    .email("editor4@maildrop.cc")
                    .name("Pero")
                    .surname("Nikic")
                    .verified(true)
                    .location(paris.toBuilder().build())
                    .build();

            baseUserRepository.save(editor);
            baseUserRepository.save(editor2);
            baseUserRepository.save(editor3);
            baseUserRepository.save(editor4);

            camundaUserService.createCamundaUser(editor);
            camundaUserService.createCamundaUser(editor2);
            camundaUserService.createCamundaUser(editor3);
            camundaUserService.createCamundaUser(editor4);
        }

        if (baseUserRepository.findAllByRole(UserRole.LECTOR).isEmpty()) {
            BaseUser lector = BaseUser.builder()
                    .role(UserRole.LECTOR)
                    .username("lector")
                    .password(passwordEncoder.encode("Lector1997!"))
                    .email("lector@maildrop.cc")
                    .name("Lector")
                    .surname("Lectorovic")
                    .verified(true)
                    .location(belgrade.toBuilder().build())
                    .build();

            baseUserRepository.save(lector);
            camundaUserService.createCamundaUser(lector);
        }

        System.out.println("Dummy data created successfully!");
    }
}
