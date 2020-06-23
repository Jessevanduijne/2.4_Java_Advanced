package vanduijne.jesse.beerapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import vanduijne.jesse.beerapi.model.Beer;
import vanduijne.jesse.beerapi.service.BeerService;

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BeerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private BeerService service;
    private Beer beer;


    @BeforeEach
    public void setup(){beer = new Beer("Oedipus", "Triple", "Thai Thai", 8, 3.50); }

    @Test
    public void getAllBeers() throws Exception {
        given(service.getAllBeers()).willReturn(Arrays.asList(beer));

        this.mockMvc.perform(
                get("/beers"))
                .andExpect((status().isOk()))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].brewery").value(beer.getBrewery())
                );
    }

    @Test
    public void getBeerById() throws Exception {
        given(service.getBeerById(0)).willReturn(beer);
        this.mockMvc.perform(
                get("/beers/{id}", 0))
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(jsonPath("$.type").value(beer.getType())
                );
    }

    @Test
    public void getBeerByBrewery() throws Exception {
        given(service.getBeerByBrewery("Oedipus")).willReturn(Arrays.asList(beer));
        this.mockMvc.perform(
                get("/beers?brewery={brewery}", "Oedipus"))
                .andExpect((status().isOk()))
                .andExpect(jsonPath("$[0].name").value(beer.getName())
                );
    }

    @Test
    public void brewBeer() throws Exception {
        var newbeer = new Beer("Uiltje", "IPA", "Bird of Prey", 5.8, 2.90);
        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(
                    post("/beers")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(mapper.writeValueAsString(newbeer)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteBeer() throws Exception {
        this.mockMvc.perform(
                delete("/beers/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted());
    }
}
