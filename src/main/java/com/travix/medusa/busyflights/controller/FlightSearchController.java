package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Controller class which provides results to API caller
 * Created by Selva on 19/11/2017.
 */
@RestController
@RequestMapping("/flights")
public class FlightSearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BusyFlightsResponse> getBusyFlights(@RequestBody final BusyFlightsRequest busyFlightsRequest) throws Exception {
        List<BusyFlightsResponse> busyFlightsResponseList = searchService.getAllFlightResponse(busyFlightsRequest);
        return busyFlightsResponseList;
    }

    @RequestMapping(value = "/crazyair", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CrazyAirResponse> getCrazyAirResponse(@RequestBody final BusyFlightsRequest busyFlightsRequest) throws Exception {
        List<CrazyAirResponse> crazyAirResponseList = Arrays.asList(new CrazyAirResponse("BAA",
                450, "B", "LHR", "PMI", "2018-08-01T09:45:45", "2018-08-01T11:45:45"));
        return crazyAirResponseList;
    }

    @RequestMapping(value = "/toughjet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ToughJetResponse> getToughjetResponse(@RequestBody final BusyFlightsRequest busyFlightsRequest) throws Exception {
        List<ToughJetResponse> toughJetResponseList = Arrays.asList(new ToughJetResponse("EZY", 300, 0, 0,
                        "STN", "AMS", "2018-12-03T10:15:30Z", "2018-12-03T12:15:30Z"),
                new ToughJetResponse("RYN", 400, 0, 0,
                        "STN", "MUN", "2018-12-03T10:15:30Z", "2018-12-03T12:15:30Z"));
        return toughJetResponseList;
    }

}
