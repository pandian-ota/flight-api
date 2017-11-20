package com.travix.medusa.busyflights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.controller.FlightSearchController;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Selva on 19/11/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FlightSearchController.class)
public class FlightSearchControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SearchService searchService;

    @Test
    public void searchFlights_ListOfFlightsReturned() throws Exception {
        List<BusyFlightsResponse> busyFlightsResponseList = Arrays.asList(new BusyFlightsResponse("TST", "CrazyAir", 2045.00,
                "LHR", "MAA", "03/12/18 10:15", "03/12/18 12:15"));
        Mockito.when(searchService.getAllFlightResponse(Mockito.any(BusyFlightsRequest.class))).thenReturn(busyFlightsResponseList);
        mvc.perform(
                post("/flights/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new BusyFlightsRequest())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
