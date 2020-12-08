# HaRT: Hardware Reservation Tool

__This tool is not yet ready. Still, under development__

The HaRT is simple reservation tool which support your Continuous Integration process. In the mind of the creators, the
tool is only a table informing the "burnout process" about the availability of resources.

The application divided for the server (backend) and command line application (user-side).

## Features

A simple tool, but have a few useful features:

* Reserve a machine for a specific amount of the time
* Protect some machines from reservations with password

## Server-side application

Default starts at :8080. Records stored in lite sql database.

### API Endpoints

Here is a simple list of available points:

All api endpoints are visible by Swagger at (by default) http://localhost:8080/swagger

## Command line application (user-side)
This is a handy tool if user don not want to use a `curl` cmds to reserve machines. 

Please use `-h`, `--help` to get a detailed information about specific cmd.