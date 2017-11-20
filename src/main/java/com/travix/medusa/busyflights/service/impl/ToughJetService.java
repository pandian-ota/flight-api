package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Created by Selva on 19/11/2017.
 */
@Service
public class ToughJetService {

    static final String endpoint = "http://localhost:8080/flights/toughjet";   //will be in DB with cache mechanism in PROD

    public static ToughJetRequest convertRequest(BusyFlightsRequest busyFlightsRequest) {
        return new ToughJetRequest(busyFlightsRequest.getOrigin(), busyFlightsRequest.getDestination(),
                busyFlightsRequest.getDepartureDate(), busyFlightsRequest.getReturnDate(), busyFlightsRequest.getNumberOfPassengers());
    }

    public static BusyFlightsResponse convertResponse(ToughJetResponse toughJetResponse) {
        return new BusyFlightsResponse(toughJetResponse.getCarrier(), "ToughJet", toughJetResponse.getBasePrice(), toughJetResponse.getDepartureAirportName(), toughJetResponse.getArrivalAirportName(),
                getFormattedDate(toughJetResponse.getOutboundDateTime()), getFormattedDate(toughJetResponse.getInboundDateTime()));
    }

    private static String getFormattedDate(String date) {
        DateTimeFormatter ukFormat =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                        .withLocale(Locale.UK)
                        .withZone(ZoneId.systemDefault());
        DateTimeFormatter format = DateTimeFormatter.ISO_INSTANT;
        return ukFormat.format(Instant.from(format.parse(date)));
    }

}
