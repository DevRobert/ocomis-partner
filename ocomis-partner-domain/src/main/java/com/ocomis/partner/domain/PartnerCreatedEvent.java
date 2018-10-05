package com.ocomis.partner.domain;

public class PartnerCreatedEvent {
    private String partnerId;
    private String name;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
