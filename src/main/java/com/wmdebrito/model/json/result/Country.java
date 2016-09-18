
package com.wmdebrito.model.json.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "attendeeCount",
        "attendees",
        "name",
        "startDate"
})
public class Country {

    @JsonProperty("attendeeCount")
    private Integer attendeeCount;
    @JsonProperty("attendees")
    private Set<String> attendees = new TreeSet<String>();
    @JsonProperty("name")
    private String name;
    @JsonProperty("startDate")
    private Date startDate;

    /**
     * @return The attendeeCount
     */
    @JsonProperty("attendeeCount")
    public Integer getAttendeeCount() {
        return attendeeCount;
    }

    /**
     * @param attendeeCount The attendeeCount
     */
    @JsonProperty("attendeeCount")
    public void setAttendeeCount(Integer attendeeCount) {
        this.attendeeCount = attendeeCount;
    }

    /**
     * @return The attendees
     */
    @JsonProperty("attendees")
    public Set<String> getAttendees() {
        return attendees;
    }

    /**
     * @param attendees The attendees
     */
    @JsonProperty("attendees")
    public void setAttendees(Set<String> attendees) {
        this.attendees = attendees;
    }

    /**
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The startDate
     */
    @JsonProperty("startDate")
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate The startDate
     */
    @JsonProperty("startDate")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


}
