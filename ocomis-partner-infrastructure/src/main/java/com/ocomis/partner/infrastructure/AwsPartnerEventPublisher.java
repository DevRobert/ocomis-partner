package com.ocomis.partner.infrastructure;

import com.ocomis.partner.domain.PartnerCreatedEvent;
import com.ocomis.partner.domain.PartnerEventPublisher;

public class AwsPartnerEventPublisher implements PartnerEventPublisher {
    @Override
    public void publishEvent(PartnerCreatedEvent event) {
        System.out.println("Partner created");
    }
}
