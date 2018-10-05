package com.ocomis.partner.api;

import com.ocomis.partner.domain.DomainException;
import com.ocomis.partner.domain.PartnerCreatedEvent;
import com.ocomis.partner.domain.PartnerEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PartnerController {
    private PartnerEventPublisher partnerEventPublisher;

    public PartnerController(PartnerEventPublisher partnerEventPublisher) {
        this.partnerEventPublisher = partnerEventPublisher;
    }

    @RequestMapping(path = "/partners", method = RequestMethod.POST)
    public CreatePartnerResponse createPartner(@RequestBody CreatePartnerRequest request) {
        if(request.getName() == null || request.getName().isEmpty()) {
            throw new DomainException("The name was not specified.");
        }

        CreatePartnerResponse response = new CreatePartnerResponse();

        response.setId("new-partner-id");

        PartnerCreatedEvent event = new PartnerCreatedEvent();
        event.setPartnerId("new-partner-id");
        event.setName(request.getName());

        this.partnerEventPublisher.publishEvent(event);

        return response;
    }

    @ExceptionHandler(DomainException.class)
    public @ResponseBody
    ResponseEntity handleException(DomainException ex) {
        return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }
}
