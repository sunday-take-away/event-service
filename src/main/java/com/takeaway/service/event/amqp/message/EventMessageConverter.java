package com.takeaway.service.event.amqp.message;

import com.takeaway.service.event.model.EventPerformed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.UnsupportedEncodingException;

public class EventMessageConverter implements MessageConverter {
    private static final Logger logger = LoggerFactory.getLogger(EventMessageConverter.class);

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        //we do not produce messages in this context (therefore not needed)
        throw new NotImplementedException();
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        try {
            return convertMetaMessage(message);
        } catch (UnsupportedEncodingException uex) {
            throw new MessageConversionException("unable to convert message", uex);
        }
    }

    private EventPerformed convertMetaMessage(Message message) throws UnsupportedEncodingException {
        String messagePayload = new String(message.getBody(), "UTF-8");

        if(logger.isDebugEnabled()) logger.debug("Converting message body payload: " + messagePayload);

        String eventType = extractMessageMetaValue(messagePayload, "EVENT_TYPE", true);
        String eventState = extractMessageMetaValue(messagePayload, "EVENT_PERFORMED", false);
        String eventId = extractMessageMetaValue(messagePayload, "EVENT_ID", false);
        String eventData = extractMessageMetaValue(messagePayload, "EVENT_DATA", false);

        if(eventData == null)
            eventData = messagePayload;

        return new EventPerformed().build(eventType, eventState, eventId, eventData);
    }

    private String extractMessageMetaValue(String value, String metaRef, Boolean forceLowerCase) {
        if(!value.contains(metaRef))
            return null;

        String metaCut = value.substring(value.indexOf(metaRef));
        String metaCutResult = (metaCut.contains("|")) ? metaCut.substring(0, metaCut.indexOf("|")) : metaCut;

        String metaValue = metaCutResult.replace(String.format("%s->", metaRef), "");

        return (forceLowerCase) ? metaValue.toLowerCase() : metaValue;
    }
}
