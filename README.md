# HaRT: Hardware Reservation Tool

__This tool is not yet ready. Still, under development__ 

The HaRT is simple reservation tool which support your Continuous Integration process. 
In the mind of the creators, the tool is only a table informing the "burnout process" about the availability of resources.

The application divided for the server (backend) and command line application (user-side).

## Server-side application
Default starts at :8080. Records stored in lite sql database.  

### API Endpoints
Here is a simple list of available points:

All api endpoints are visible by Swagger at (by default) http://localhost:8080/swagger


#### GET api/v1/reservations
Return a list of all available machines for reservation with theirs status (unavailable machines excluded)

#### GET api/v1/reserve/{machineId}
Return an entity of the specific machine with Id

#### POST api/v1/reserve/{machineId}
Payload: TBD

#### GET api/v1/reserve/{machineId}/release
Release a reserved machine

#### GET api/v1/machines
Return a list of all machines

#### POST api/v1/machines
Add a new machine to the list

#### GET api/v1/machines/{machineId}
Detailed information about machines

#### GET api/v1/machines/{machineId}/delete
Delete a machine

#### GET api/v1/machines/{machineId}/enable
Enable a machine 

#### GET api/v1/machines/{machineId}/disable
Disable a machine (will not be visible as ready for reservation)

#### GET api/v1/machines/{machineId}/protect
Protect a machine with a master key. Reservations will be available only with reservation key

#### GET api/v1/machines/{machineId}/unprotect
Unprotect a machine from the unauthorized reservation request


## Command line application (user-side)
This is a handy tool if user don not want to use a `curl` cmds to reserve machines. 

Please use `-h`, `--help` to get a detailed information about specific cmd.