
package com.hubspot.testapi.api;

import java.util.List;
import com.hubspot.testapi.models.Partner;

@SuppressWarnings("unused")
public class PartnersListResponse {

    private List<Partner> partners;

    public List<Partner> getPartners() {
        return partners;
    }

    public static class Builder {

        private List<Partner> partners;

        public PartnersListResponse.Builder withPartners(List<Partner> partners) {
            this.partners = partners;
            return this;
        }

        public PartnersListResponse build() {
            PartnersListResponse partnersListResponse = new PartnersListResponse();
            partnersListResponse.partners = partners;
            return partnersListResponse;
        }

    }

}
