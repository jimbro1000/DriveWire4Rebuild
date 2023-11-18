# Java DriveWire 4 Rebuild #

This project is an experimental redesign
and rebuild of the DriveWire 4 package
by Aaron Wolfe based on the original work
by Boisy Pitre.

See https://github.com/boisy/DriveWire/wiki/DriveWire-Specification
for the original DriveWire 4 specification

## Architecture ##

There are a few goals to this redesign:

1. Provide a consistent set of API endpoints for handling commands. This is
to facilitate multiple user interfaces (e.g. CLI, remote, web, desktop)
2. Remove explicit thread handling by leveraging the Spring framework and make
everything event based
3. Maintain an extensible framework for commands, metrics and attached devices

Ultimately the final solution will likely consist of multiple services but the 
core of the design is the main communication interface that provides ports
accessible to the client computers over RS232 and TCPIP

Attached devices will be handled either as a group or discrete services for 
floppy drives, hard drives, printers, etc.

User interfaces will be abstraction layers over the service core. The innermost
will provide an object rich command interface that can be exploited by specific
interface types

## Hosting ##

The design is intended, in this form, to be run as a reusable container but 
ultimately can be run-up in its native jar form

The service based approach provides a run-anywhere concept that allows devices
to exist anywhere - your floppy and hard drives could be cloud hosted provided
your local core service (assuming you are using RS232 serial) has access to
the device services. If you are using the TCPIP ports against a virtual client
then the entire service suite could be cloud hosted.