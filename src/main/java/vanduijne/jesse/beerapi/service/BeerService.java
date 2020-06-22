package vanduijne.jesse.beerapi.service;

import org.springframework.stereotype.Service;
import vanduijne.jesse.beerapi.model.Beer;

import java.util.Arrays;
import java.util.List;

@Service
public class BeerService {

    List<Beer> beers;

    public BeerService() {
        this.beers = Arrays.asList(
                new Beer(1, "Brouwerij 't IJ", "witbier", "IJwit",  6.5),
                new Beer(2, "Brouwerij 't IJ", "triple", "Zatte", 8.0),
                new Beer(3, "Brouwerij 't IJ", "dubbel", "Nate", 6.5),
                new Beer(4, "Brouwerij 't IJ", "pale ale", "Flink", 6.5)
        );
    }

    public List<Beer> getAllBeers() {
        return beers;
    }
}
