
package com.wmdebrito.model.json.list;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "partners"
})
public class ListOfPartners {

    @JsonProperty("partners")
    private List<Partner> partners = new ArrayList<Partner>();

    /**
     * @return The partners
     */
    @JsonProperty("partners")
    public List<Partner> getPartners() {
        return partners;
    }

    /**
     * @param partners The partners
     */
    @JsonProperty("partners")
    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }


}
