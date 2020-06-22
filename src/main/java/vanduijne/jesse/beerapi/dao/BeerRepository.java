package vanduijne.jesse.beerapi.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vanduijne.jesse.beerapi.model.Beer;

import java.util.List;

@Repository
public interface BeerRepository extends CrudRepository<Beer, Long> {
    List<Beer> getAllByPriceGreaterThanEqualOrderById(double value);
}
