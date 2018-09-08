package com.ocomis.partner.domain;

public interface PartnerEventPublisher {
    void publishEvent(PartnerCreatedEvent event);
}
