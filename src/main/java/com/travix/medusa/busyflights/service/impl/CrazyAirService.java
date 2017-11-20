package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Created by Selva on 19/11/2017.
 */
@Service
public class CrazyAirService {

    static final String endpoint = "http://localhost:8080/flights/crazyair";   //will be in DB with cache mechanism in PROD

    public static CrazyAirRequest convertRequest(BusyFlightsRequest busyFlightsRequest) {
        return new CrazyAirRequest(busyFlightsRequest.getOrigin(), busyFlightsRequest.getDestination(),
                busyFlightsRequest.getDepartureDate(), busyFlightsRequest.getReturnDate(), busyFlightsRequest.getNumberOfPassengers());
    }

    public static BusyFlightsResponse convertResponse(CrazyAirResponse crazyAirResponse) {
        return new BusyFlightsResponse(crazyAirResponse.getAirline(), "CrazyAir", crazyAirResponse.getPrice(), crazyAirResponse.getDepartureAirportCode(),
                crazyAirResponse.getDestinationAirportCode(), getFormattedDate(crazyAirResponse.getDepartureDate()), getFormattedDate(crazyAirResponse.getArrivalDate()));
    }

    private static String getFormattedDate(String date) {
        DateTimeFormatter ukFormat =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                        .withLocale(Locale.UK)
                        .withZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return ukFormat.format(localDateTime);
    }
}
