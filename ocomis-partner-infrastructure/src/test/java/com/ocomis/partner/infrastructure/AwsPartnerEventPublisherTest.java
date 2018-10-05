package com.ocomis.partner.infrastructure;

import com.ocomis.partner.domain.PartnerCreatedEvent;
import org.junit.Test;

public class AwsPartnerEventPublisherTest {
    @Test
    public void testPublishEvent() {
        PartnerCreatedEvent event = new PartnerCreatedEvent();
        event.setPartnerId("test-partner-id");

        AwsPartnerEventPublisher eventPublisher = new AwsPartnerEventPublisher();
        eventPublisher.publishEvent(event);
    }
}
