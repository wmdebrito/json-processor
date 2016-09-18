package com.wmdebrito.processor;


import com.wmdebrito.model.json.list.ListOfPartners;
import com.wmdebrito.model.json.list.Partner;
import com.wmdebrito.model.json.result.Country;
import com.wmdebrito.model.json.result.Result;
import com.wmdebrito.util.HttpHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Processor implements CommandLineRunner {

    private static final Long ONE_DAY = 24 * 60 * 60 * 1000L;


    @Autowired
    private HttpHandler httpHandler;


    public void run(String... args) throws Exception {
        Result result = getResult(httpHandler.getListOfPartners());
        httpHandler.sendResults(result);

    }

    public Result getResult(ListOfPartners listOfPartners) {

        Result result = new Result();

        List<Country> countries = new ArrayList<Country>();

        Map<String, Map<Date, Set<Partner>>> partnerPerDatePerCountry = new HashMap<String, Map<Date, Set<Partner>>>();

        for (Partner p : listOfPartners.getPartners()) {
            if (!partnerPerDatePerCountry.containsKey(p.getCountry())) {
                partnerPerDatePerCountry.put(p.getCountry(), new TreeMap<Date, Set<Partner>>());
            }

            Map<Date, Set<Partner>> datesPerCountryMap = partnerPerDatePerCountry.get(p.getCountry());

            for (Date date : p.getAvailableDates()) {
                if (!datesPerCountryMap.containsKey(date)) {
                    datesPerCountryMap.put(date, new HashSet<Partner>());
                }
                Set<Partner> partnersPerDate = datesPerCountryMap.get(date);
                partnersPerDate.add(p);
            }

        }

        for (String country : partnerPerDatePerCountry.keySet()) {
            Country countryResult = new Country();
            countries.add(countryResult);

            Set<Partner> finalSet = new HashSet<Partner>();
            Date startDate = null;

            for (Date date : partnerPerDatePerCountry.get(country).keySet()) {
                Date nextDay = new Date(date.getTime() + ONE_DAY);

                if (partnerPerDatePerCountry.get(country).containsKey(date) && partnerPerDatePerCountry.get(country).containsKey(nextDay)) {
                    Set<Partner> set1 = partnerPerDatePerCountry.get(country).get(date);
                    Set<Partner> set2 = partnerPerDatePerCountry.get(country).get(nextDay);

                    Set<Partner> intersection = new HashSet<Partner>(set1);

                    intersection.retainAll(set2);
                    if (finalSet.size() < intersection.size()) {
                        finalSet = intersection;
                        startDate = date;
                    }
                }
            }

            countryResult.setName(country);
            countryResult.setAttendeeCount(finalSet.size());

            Set<String> attendees = new TreeSet<String>();
            for (Partner attendee : finalSet) {
                attendees.add(attendee.getEmail());
            }

            countryResult.setAttendees(attendees);
            countryResult.setStartDate(startDate);

        }
        result.setCountries(countries);
        return result;
    }
}