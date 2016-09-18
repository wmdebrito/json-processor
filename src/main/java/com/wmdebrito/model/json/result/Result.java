
package com.wmdebrito.model.json.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "countries"
})
public class Result {

    @JsonProperty("countries")
    private List<Country> countries = new ArrayList<Country>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The countries
     */
    @JsonProperty("countries")
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * @param countries The countries
     */
    @JsonProperty("countries")
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }


}
