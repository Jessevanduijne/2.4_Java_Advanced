package vanduijne.jesse.beerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vanduijne.jesse.beerapi.model.Beer;
import vanduijne.jesse.beerapi.service.BeerService;

import java.util.List;

@Controller
@RequestMapping("beers")
public class BeerController {

    @Autowired
    private BeerService beerService;

    public BeerController(BeerService beerService) { this.beerService = beerService; }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBeers(){
        List<Beer> beers = beerService.getAllBeers();
        return ResponseEntity.status(HttpStatus.OK).body(beers.toString());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetBeerByID(@PathVariable long id) {
        try {
            Beer beer = beerService.getBeerById(id);
            return ResponseEntity.status(HttpStatus.OK).body(beer);
        }
        catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity brewBeer (@RequestBody Beer beer) {
        beerService.brewBeer(beer);
        return ResponseEntity.status(HttpStatus.CREATED).body(beer.getId());
    }

    @RequestMapping(value = "{id}/value", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStockByBeerId(@PathVariable long id) {
        int value = beerService.valueByBeerId(id);
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "brewery")
    public ResponseEntity getBeerByBrewery(@RequestParam("brewery") String brewery){
        List<Beer> breweryBeers = beerService.getBeerByBrewery(brewery);
        return ResponseEntity.status(HttpStatus.OK).body(breweryBeers.toString());
    }

    @RequestMapping(value = "max", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBeerMaxPercentage() {
        Beer heaviestBeer = beerService.getHeaviestBeer();
        return ResponseEntity.status(HttpStatus.OK).body(heaviestBeer.toString());
    }
}
