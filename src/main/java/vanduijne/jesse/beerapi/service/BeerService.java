package vanduijne.jesse.beerapi.service;

import org.springframework.stereotype.Service;
import vanduijne.jesse.beerapi.model.Beer;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BeerService {

    List<Beer> beers;

    public BeerService() {
        this.beers = Arrays.asList(
                new Beer(1, "Brouwerij 't IJ", "witbier", "IJwit",  6.5),
                new Beer(2, "Brouwerij 't IJ", "triple", "Zatte", 8.0),
                new Beer(3, "Brouwerij 't IJ", "dubbel", "Nate", 6.5),
                new Beer(4, "Brouwerij 't IJ", "pale ale", "Flink", 4.5),
                new Beer(4, "Uiltje", "IPA", "Bird of Prey", 5.8)
        );
    }

    public List<Beer> getAllBeers() {
        return beers;
    }

    public List<Beer> getBeerByBrewery(String brewery){
        var filteredBeers = beers.stream()
                .filter(b -> b.getBrewery().toLowerCase().equals(brewery.toLowerCase()))
                .collect(Collectors.toList());
        return filteredBeers;
    }

    public Beer getHeaviestBeer() {
        Beer heaviest = beers.stream()
                .max(Comparator.comparing(Beer::getPercentage))
                .orElseThrow(NoSuchElementException::new);
        return heaviest;
    }
}
