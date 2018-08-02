# event-service

A rest service for events. 

This is project is intended to be run in conjunction with other services.
For full installation of all relevant software, how to run and install, see:

https://github.com/sunday-take-away/employee-containers

# Rest Service Operations

## Get
List all events for a specified id for event type. 

Example
* EVENT-TYPE = employee 
* ID = 5b62f2b86f5e5d00010c355f
```
http://localhost:8002/event/{EVENT-TYPE}/{ID}
```