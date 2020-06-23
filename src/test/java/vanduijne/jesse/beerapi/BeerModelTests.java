package vanduijne.jesse.beerapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vanduijne.jesse.beerapi.model.Beer;

import static org.junit.jupiter.api.Assertions.*;

public class BeerModelTests {

    private Beer beer;

    @BeforeEach
    public void Setup(){
        beer = new Beer();
    }

    @Test
    public void createBeerShouldNotBeNull(){
        assertNotNull(beer);
    }

    @Test
    public void settingPercentageAboveTwentyShouldThrowException(){

        // Throw exception on purpose:
        Exception exception = assertThrows(IllegalArgumentException.class, () -> beer.setPercentage(21));

        // Check if error is the error that you'd expect:
        assertEquals("Beer shouldn't contain more than 20% alcohol. That's dangerous", exception.getMessage());
    }
}
