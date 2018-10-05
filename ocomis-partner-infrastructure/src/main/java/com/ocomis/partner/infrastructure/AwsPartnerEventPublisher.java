package com.ocomis.partner.infrastructure;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocomis.partner.domain.PartnerCreatedEvent;
import com.ocomis.partner.domain.PartnerEventPublisher;

import java.util.UUID;

public class AwsPartnerEventPublisher implements PartnerEventPublisher {
    @Override
    public void publishEvent(PartnerCreatedEvent event) {
        final String accessKey = "--";
        final String secretKey = "--";
        final String region = "us-east-1";

        final AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        final AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();

        final String queueUrl = amazonSQS.getQueueUrl("ocomis-partner-Partner.fifo").getQueueUrl();
        final String messageBody;

        try {
            messageBody = new ObjectMapper().writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        SendMessageRequest sendMessageRequest = new SendMessageRequest(queueUrl, messageBody);
        sendMessageRequest.setMessageGroupId("Partner");
        sendMessageRequest.setMessageDeduplicationId(UUID.randomUUID().toString());

        amazonSQS.sendMessage(sendMessageRequest);
    }
}
