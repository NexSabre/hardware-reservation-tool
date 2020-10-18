# HaRT: Hardware Reservation Tool

__This tool is not yet ready. Still, under development__ 

The HaRT is simple reservation tool which support your Continuous Integration process. 
In the mind of the creators, the tool is only a table informing the "burnout process" about the availability of resources.

## API Endpoints
Here is a simple list of available points:

### GET api/v1/reservations
Return a list of all available machines for reservation with theirs status

### GET api/v1/reserve/{machineId}
Return an entity of the specific machine with Id

### POST api/v1/reserve/{machineId}
Payload: TBD
