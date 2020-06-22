package vanduijne.jesse.beerapi.configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import vanduijne.jesse.beerapi.dao.BeerRepository;
import vanduijne.jesse.beerapi.dao.StockRepository;
import vanduijne.jesse.beerapi.model.Beer;
import vanduijne.jesse.beerapi.model.Stock;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@ConditionalOnProperty(prefix = "beershop.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
@Transactional
public class BeerApplicationRunner implements ApplicationRunner {

    private BeerRepository beerRepository;
    private StockRepository stockRepository;

    public BeerApplicationRunner(BeerRepository beerRepository, StockRepository stockRepository) {
        this.beerRepository = beerRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Beer> beers =
                Arrays.asList(
                new Beer("Brouwerij 't IJ", "witbier", "IJwit",  6.5, 3.50),
                new Beer("Brouwerij 't IJ", "triple", "Zatte", 8.0, 3.75),
                new Beer("Brouwerij 't IJ", "dubbel", "Nate", 6.5, 3.20),
                new Beer( "Brouwerij 't IJ", "pale ale", "Flink", 4.5, 3.10),
                new Beer("Uiltje", "IPA", "Bird of Prey", 5.8, 2.90)
        );

        beers.forEach(beerRepository::save);

        beerRepository.findAll().forEach(System.out::println);

        beerRepository.findAll().forEach(beer -> stockRepository.save(new Stock(new Random().nextInt(100), beer)));

        stockRepository.findAll().forEach(System.out::println);

        Iterable<Stock> stocks = stockRepository.getAllByQuantityGreaterThanEqualOrderByQuantity(30);
        stocks.forEach(System.out::println);

        int quantity = stockRepository.getStockValueByBeerId(1000001L);
        System.out.println("Stock quantity: " + quantity);
    }
}
