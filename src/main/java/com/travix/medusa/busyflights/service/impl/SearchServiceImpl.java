package com.travix.medusa.busyflights.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.GenericHttpClientService;
import com.travix.medusa.busyflights.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Service class which calls all the external APIs
 * Created by Selva on 19/11/2017.
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger LOG = Logger.getLogger(SearchServiceImpl.class.getName());

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private GenericHttpClientService httpClientService;

    @Override
    public List<BusyFlightsResponse> getAllFlightResponse(BusyFlightsRequest busyFlightsRequest) throws Exception {
        List<BusyFlightsResponse> busyFlightsResponseList1 = null;
        if (Boolean.TRUE) { //this IF condition will be DB driven in PROD code, to enable/disable call to supplier
            List<CrazyAirResponse> crazyAirResponseList = mapper.readValue(httpClientService.getResults(busyFlightsRequest, CrazyAirService.endpoint), new TypeReference<List<CrazyAirResponse>>() {
            });
            busyFlightsResponseList1 = crazyAirResponseList.stream().map(CrazyAirService::convertResponse).collect(Collectors.toList());
        }
        List<BusyFlightsResponse> busyFlightsResponseList2 = null;
        if (Boolean.TRUE) { //this IF condition will be DB driven in PROD code, to enable/disable call to supplier
            List<ToughJetResponse> toughJetResponseList = mapper.readValue(httpClientService.getResults(busyFlightsRequest, ToughJetService.endpoint), new TypeReference<List<ToughJetResponse>>() {
            });
            busyFlightsResponseList2 = toughJetResponseList.stream().map(ToughJetService::convertResponse).collect(Collectors.toList());
        }
        if (busyFlightsResponseList1 != null && busyFlightsResponseList2 != null) {
            busyFlightsResponseList1.addAll(busyFlightsResponseList2);
        }
        Comparator<BusyFlightsResponse> byPrice = (b1, b2) -> Double.compare(b1.getFare(), b2.getFare());
        busyFlightsResponseList1 = busyFlightsResponseList1.stream().sorted(byPrice).collect(Collectors.toList());
        return busyFlightsResponseList1;
    }

}
