package com.ocomis.partner.api;

import com.ocomis.partner.domain.PartnerCreatedEvent;
import com.ocomis.partner.domain.PartnerEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartnerController {
    private PartnerEventPublisher partnerEventPublisher;

    public PartnerController(PartnerEventPublisher partnerEventPublisher) {
        this.partnerEventPublisher = partnerEventPublisher;
    }

    @RequestMapping(path = "/partners", method = RequestMethod.POST)
    public CreatePartnerResponse createPartner() {
        CreatePartnerResponse response = new CreatePartnerResponse();

        response.setId("new-partner-id");

        PartnerCreatedEvent event = new PartnerCreatedEvent();

        this.partnerEventPublisher.publishEvent(event);

        return response;
    }
}
