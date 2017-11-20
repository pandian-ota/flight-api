package com.travix.medusa.busyflights.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.service.GenericHttpClientService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 * Generic class which handles calls for Http
 * Created by Selva on 19/11/2017.
 */
@Service
public class GenericHttpClientImpl implements GenericHttpClientService {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getResults(BusyFlightsRequest busyFlightsRequest, String endpoint) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(endpoint);
        StringEntity jsonRequest = new StringEntity(mapper.writeValueAsString(busyFlightsRequest));
        request.addHeader("content-type", "application/json");
        request.setEntity(jsonRequest);
        HttpResponse response = httpClient.execute(request);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }
}
