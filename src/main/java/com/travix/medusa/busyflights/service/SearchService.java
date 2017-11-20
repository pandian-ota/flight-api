package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.List;

/**
 * Created by Selva on 19/11/2017.
 */
public interface SearchService {

    List<BusyFlightsResponse> getAllFlightResponse(BusyFlightsRequest busyFlightsRequest) throws Exception;
}
