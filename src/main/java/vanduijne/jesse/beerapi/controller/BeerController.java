package vanduijne.jesse.beerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vanduijne.jesse.beerapi.model.Beer;
import vanduijne.jesse.beerapi.service.BeerService;

import java.util.List;

@Controller
@RequestMapping("beers")
public class BeerController {

    @Autowired
    private BeerService service;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBeers(){
        List<Beer> beers = service.getAllBeers();
        return ResponseEntity.status(200).body(beers);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "brewery")
    public ResponseEntity getBeerByBrewery(@RequestParam("brewery") String brewery){
        List<Beer> breweryBeers = service.getBeerByBrewery(brewery);
        return ResponseEntity.status(200).body(breweryBeers);
    }

    @RequestMapping(value = "max", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBeerMaxPercentage() {
        Beer heaviestBeer = service.getHeaviestBeer();
        return ResponseEntity.status(200).body(heaviestBeer);
    }
}
