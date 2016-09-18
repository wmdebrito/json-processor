
package com.wmdebrito.model.json.list;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "firstName",
        "lastName",
        "email",
        "country",
        "availableDates"
})
public class Partner {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("country")
    private String country;
    @JsonProperty("availableDates")
    private List<Date> availableDates = new ArrayList<Date>();


    /**
     * @return The firstName
     */
    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The firstName
     */
    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return The lastName
     */
    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName The lastName
     */
    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The email
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The country
     */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The availableDates
     */
    @JsonProperty("availableDates")
    public List<Date> getAvailableDates() {
        return availableDates;
    }

    /**
     * @param availableDates The availableDates
     */
    @JsonProperty("availableDates")
    public void setAvailableDates(List<Date> availableDates) {
        this.availableDates = availableDates;
    }


}
