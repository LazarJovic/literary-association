package goveed20.LiteraryAssociationApplication.services;

import goveed20.LiteraryAssociationApplication.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<String> getGenres() {
        System.out.println("usao");
        return genreRepository.findAll().stream().map(g -> g.getGenre().serbianName).collect(Collectors.toList());
    }

}
