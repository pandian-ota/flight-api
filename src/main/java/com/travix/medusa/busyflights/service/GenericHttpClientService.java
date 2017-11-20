package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;

/**
 * Created by Selva on 19/11/2017.
 */
public interface GenericHttpClientService {
    String getResults(BusyFlightsRequest busyFlightsRequest, String endpoint) throws Exception;
}
