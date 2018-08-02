package com.takeaway.service.event.repository;

import com.takeaway.service.event.model.EventPerformed;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class EventRepository {
    public static final String REPOSITORY = "event";

    private InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    public void write(EventPerformed event) {
        final Point p = Point.measurement(REPOSITORY)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("state", event.getState())
                .tag("type", event.getType())
                .tag("id", event.getId())
                .addField("data", event.getData())
                .build();
        influxDBTemplate.write(p);
    }

    public List<EventPerformed> read(String query) {
        QueryResult queryResult = influxDBTemplate.query(new Query(query, "events"));
        return resultMapper.toPOJO(queryResult, EventPerformed.class);
    }
}
