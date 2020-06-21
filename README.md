# SchedulerAPI

How to start the SchedulerAPI application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/SchedulerAPI-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:9090`

Swagger
----

Swagger UI can be accessed via:
> http://localhost:9090/swagger

Health Check
---

To see your applications health enter url `http://localhost:9091/healthcheck`
