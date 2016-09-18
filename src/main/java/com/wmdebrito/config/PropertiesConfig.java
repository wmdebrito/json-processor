package com.wmdebrito.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "api")
@Component
public class PropertiesConfig {

    private String listOfPartnersUrl;

    private String resultsUrl;

    private String userKey;

    private String dateFormat;

    public String getListOfPartnersUrl() {
        return listOfPartnersUrl;
    }

    public void setListOfPartnersUrl(String listOfPartnersUrl) {
        this.listOfPartnersUrl = listOfPartnersUrl;
    }


    public String getResultsUrl() {
        return resultsUrl;
    }

    public void setResultsUrl(String resultsUrl) {
        this.resultsUrl = resultsUrl;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
