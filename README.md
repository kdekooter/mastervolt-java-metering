mastervolt-java-metering
========================

This is a client library written in Java enabling a connection to a MasterVolt inverter.

Credits & thanks go to:
- Jorg Jansen http://www.zonnigdruten.nl - author of a Python script
- Marco Bakker http://zon.mbsoft.nl - editor of a bash script

The library connects to a RS-485-to-TCP/IP converter like the ATC-1000.

The code is tested with a MasterVolt XS3200 inverter.

Feel free to fork and improve the code!

usage
=====

This library is built with Maven. To include it in your project add the following repository and dependency:

```xml
<repository>
    <id>boplicity.nl</id>
    <name>boplicity snapshots</name>
    <url>https://boplicity.nl/nexus/content/repositories/snapshots/</url>
</repository>

<dependency>
    <groupId>org.boplicity</groupId>
    <artifactId>mastervolt-java-metering</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

Or check out the code and install it in your local repository using ```mvn install```