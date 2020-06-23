package vanduijne.jesse.beerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vanduijne.jesse.beerapi.dao.BeerRepository;
import vanduijne.jesse.beerapi.dao.StockRepository;
import vanduijne.jesse.beerapi.model.Beer;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BeerService {

   // List<Beer> beers;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<Beer> getAllBeers(){
        return (List<Beer>) beerRepository.findAll();
    }

    public Beer getBeerById(long id){
        return beerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void brewBeer(Beer beer) {
        beerRepository.save(beer);
        System.out.println("Saved this new beer: " + beer);
    }

    public int valueByBeerId(long id) {
        return stockRepository.getStockValueByBeerId(id);
    }

    public List<Beer> getBeersByMinimumPrice(double minimum) {
        return beerRepository.getAllByPriceGreaterThanEqualOrderById(minimum);
    }

    public List<Beer> getBeerByBrewery(String brewery){
        var filteredBeers =  getAllBeers().stream()
                .filter(b -> b.getBrewery().toLowerCase().equals(brewery.toLowerCase()))
                .collect(Collectors.toList());
        return filteredBeers;
    }

    public Beer getHeaviestBeer() {
        Beer heaviest = getAllBeers().stream()
                .max(Comparator.comparing(Beer::getPercentage))
                .orElseThrow(NoSuchElementException::new);
        return heaviest;
    }

    public void deleteBeer (long id) {
        beerRepository.deleteById(id);
    }
}
