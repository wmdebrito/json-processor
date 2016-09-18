package com.wmdebrito.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmdebrito.model.json.list.ListOfPartners;
import com.wmdebrito.model.json.result.Country;
import com.wmdebrito.model.json.result.Result;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertTrue;


public class ProcessorTest {
    @Test
    public void getResult() throws Exception {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ObjectMapper mapper = getMapper();

        ListOfPartners partners = mapper.readValue(contextClassLoader.getResourceAsStream("partners.json"), ListOfPartners.class);

        Result results = mapper.readValue(contextClassLoader.getResourceAsStream("result.json"), Result.class);
        Processor processor = new Processor();

        Result resultToCompare = processor.getResult(partners);

        assertTrue(results.getCountries().size() == resultToCompare.getCountries().size());
        for (Country country : results.getCountries()) {
            boolean found = false;
            for (Country country2 : resultToCompare.getCountries()) {
                if (country.getName().equalsIgnoreCase(country2.getName())) {
                    assertTrue(country.getAttendeeCount().equals(country2.getAttendeeCount()));
                    if (country.getStartDate() != null)
                        assertTrue(country.getStartDate().equals(country2.getStartDate()));

                    assertTrue(country.getAttendees().equals(country2.getAttendees()));
                    found = true;
                }
            }
            assertTrue(found);
        }


    }

    @Test(expected = NullPointerException.class)
    public void getResultNull() throws Exception {
        Processor processor = new Processor();

        Result resultToCompare = processor.getResult(null);

    }

    private ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        return mapper;
    }

}