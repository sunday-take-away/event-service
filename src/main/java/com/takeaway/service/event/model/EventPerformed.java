package com.takeaway.service.event.model;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

@Measurement(name = "event")
public class EventPerformed {

    @Column(name = "time")
    private Instant time;

    @Column(name = "type", tag = true)
    private String type;

    @Column(name = "state", tag = true)
    private String state;

    @Column(name = "id", tag = true)
    private String id;

    @Column(name = "data")
    private String data;

    public Instant getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getState() {
        return state;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public EventPerformed build(String type, String state, String id, String data) {
        this.type = type;
        this.state = state;
        this.id = id;
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "EventPerformed{" +
                "time=" + time +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", id='" + id + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}