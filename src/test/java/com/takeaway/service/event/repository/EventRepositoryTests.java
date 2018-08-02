package com.takeaway.service.event.repository;

import com.takeaway.service.event.model.EventPerformed;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventRepositoryTests {

    @Autowired
    private EventRepository eventRepository;

    @Ignore("issue with binding without full spring boot context, run manually")
    @Test
    public void shouldSaveEventToDatabase() {
        EventPerformed event = new EventPerformed().build("employee", "CREATED", "1", "{some json value}");
        eventRepository.write(event);
        List<EventPerformed> results = eventRepository.read("SELECT * FROM event WHERE type = 'employee'");
        assertTrue(results.size() > 0);
    }

    @Ignore("issue with binding without full spring boot context, run manually")
    @Test
    public void shouldReadEventDataFromDatabase() {
        List<EventPerformed> results = eventRepository.read("SELECT * FROM event WHERE type = 'employee' ORDER BY time ASC");
        assertTrue(results.size() > 0);
    }
}
