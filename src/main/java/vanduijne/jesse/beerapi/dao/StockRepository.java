package vanduijne.jesse.beerapi.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vanduijne.jesse.beerapi.model.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

    Iterable<Stock> getAllByQuantityGreaterThanEqualOrderByQuantity(int minimum);

    @Query("select s.quantity * b.price from Stock s, Beer b where s.beer.id = b.id and s.beer.id = ?1")
    int getStockValueByBeerId(Long id);

//    @Query("select s.quantity from Stock s, Beer b where s.beer.id = b.id and s.beer.id = ?1")
//    int getStockByBeerId(Long id);
}
