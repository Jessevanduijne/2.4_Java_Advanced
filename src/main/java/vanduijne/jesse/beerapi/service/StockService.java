package vanduijne.jesse.beerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vanduijne.jesse.beerapi.dao.StockRepository;
import vanduijne.jesse.beerapi.model.Stock;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        System.out.println("StockService: In service");
        return (List<Stock>) stockRepository.findAll();
    }
}
