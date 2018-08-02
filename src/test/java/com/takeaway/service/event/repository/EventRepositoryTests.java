package com.takeaway.service.event.repository;

import com.takeaway.service.event.model.EventPerformed;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//todo: use more isolated tests

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventRepositoryTests {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void shouldSaveEventToDatabase() {
        EventPerformed event = new EventPerformed().build("employee", "CREATED", "1", "{some json value}");
        eventRepository.write(event);
    }

    @Test
    public void shouldReadEventDataFromDatabase() {
        List<EventPerformed> results = eventRepository.read("SELECT * FROM event WHERE type = 'employee' AND id = '5b606c9d03b22262d5b8beae' ORDER BY time ASC");
        System.out.print(results);
    }
}
