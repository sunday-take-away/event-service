package com.takeaway.service.event.amqp.message;

import com.takeaway.service.event.model.EventPerformed;
import org.junit.Test;
import org.springframework.amqp.core.Message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventMessageConverterTests {

    @Test
    public void shouldConvertQueueMessageToEvent() throws Exception {
        byte[] payload = "EVENT_TYPE->Employee|EVENT_ID->1|EVENT_PERFORMED->CREATED|EVENT_DATA->{\"id\":1,\"email\":\"april.fool@icloud.com\"}".getBytes("UTF-8");

        Message message = new Message(payload, null);
        assertNotNull(message);

        EventPerformed event = (EventPerformed) new EventMessageConverter().fromMessage(message);
        assertNotNull(event);

        assertEquals("1", event.getId());
        assertEquals("employee", event.getType());
        assertEquals("CREATED", event.getState());
        assertEquals("{\"id\":1,\"email\":\"april.fool@icloud.com\"}",  event.getData());
    }

    @Test
    public void shouldConvertNoMetaQueueMessageToEventWithDataOnly() throws Exception {
        byte[] payload = "{\"id\":1,\"email\":\"april.fool@icloud.com\"}".getBytes("UTF-8");

        Message message = new Message(payload, null);
        assertNotNull(message);

        EventPerformed event = (EventPerformed) new EventMessageConverter().fromMessage(message);
        assertNotNull(event);

        assertEquals(null, event.getId());
        assertEquals(null, event.getType());
        assertEquals(null, event.getState());
        assertEquals("{\"id\":1,\"email\":\"april.fool@icloud.com\"}",  event.getData());
    }
}
