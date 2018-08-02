package com.takeaway.service.event.amqp;

import com.takeaway.service.event.model.EventPerformed;
import com.takeaway.service.event.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventMessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EventMessageConsumer.class);

    @Autowired
    private EventService eventService;

    @RabbitListener(queues = "service-exchange.event", containerFactory = "eventConsumerFactory")
    public void processMessage(EventPerformed event) {
        if(logger.isDebugEnabled()) logger.debug(String.format("EventMessage received: %s", event));
        eventService.saveEvent(event);
    }
}
