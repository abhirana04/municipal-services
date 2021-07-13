package org.egov.gcservice.consumer;

import java.util.HashMap;

import org.egov.gcservice.service.PaymentUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiptConsumer {
	@Autowired
	private PaymentUpdateService paymentUpdateService;
	
	@KafkaListener(topics = {"${kafka.topics.receipt.create}"})
    public void listenPayments(final HashMap<String, Object> record) {
        paymentUpdateService.process(record);
    }
}
