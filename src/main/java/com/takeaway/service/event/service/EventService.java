package com.takeaway.service.event.service;

import com.takeaway.service.event.model.EventPerformed;
import com.takeaway.service.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventPerformed> findAllEventsForTypeAndId(String type, String id) {
        return eventRepository.read(String.format("SELECT * FROM event WHERE type = '%s' AND id = '%s' ORDER BY time ASC", type, id));
    }

    public void saveEvent(EventPerformed event) {
        eventRepository.write(event);
    }
}
