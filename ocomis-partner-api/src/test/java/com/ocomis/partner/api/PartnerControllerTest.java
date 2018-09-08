package com.ocomis.partner.api;

import com.ocomis.partner.domain.PartnerEventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PartnerControllerTest {
    @Autowired
    protected MockMvc mvc;

    @MockBean
    protected PartnerEventPublisher partnerEventPublisher;

    @Test
    public void createPartnerAcceptsRequestAndReturnsPartnerId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/partners")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty());
    }

    @Test
    public void createPartnerPublishesEvent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/partners")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(partnerEventPublisher, times(1)).publishEvent(any());
    }
}
