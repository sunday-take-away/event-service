package com.takeaway.service.event.controller;

import com.takeaway.service.event.model.EventPerformed;
import com.takeaway.service.event.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping("/event/employee/{id}")
    public List<EventPerformed>  event(@PathVariable String id) {
        logger.debug("requesting events for id:" + id);
        return eventService.findAllEventsForTypeAndId("employee", id);
    }
}
