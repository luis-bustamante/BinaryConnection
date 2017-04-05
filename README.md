# BinaryConnection
WSO2 data-bridge BinaryDataReceiver SSL and TCP Transport tester

# Usage
$ java -jar BinaryConnection-1.0.jar --help
usage: Main
 -?,--help              Show this help.
 -h,--hostname <arg>    Optional. Server Hostname. ex: -h 127.0.0.1
 -k,--keyword <arg>     Optional. User password. ex: -k admin
 -p,--port <arg>        Optional. Port number. ex: -p 9711
 -t,--transport <arg>   Required. Binary Transport: -t TCP|SSL
 -u,--username <arg>    Optional. Username. ex: -u admin

# Examples
1) Connect to BinaryDataReceiver Binary SSL Transport on port 9711:

$ java -jar BinaryConnection-1.0.jar -t SSL -h localhost -u admin -k admin -p 9711

SSL session retrieved: a239c757-8a9d-4840-ab79-7170372dfe39
Tested successfully SSL on host: localhost, port: 9711

2) Connect to BinaryDataReceiver Binary TCP Transport on port 9611 using default user and localhost:

$ java -jar BinaryConnection-1.0.jar -t TCP

TCP session retrieved: bdbbb83a-3da2-4f43-8a85-b412ae6533eb
Tested successfully TCP on host: localhost, port: 9611

