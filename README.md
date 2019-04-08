### Project for compairing event sourcing implementations

Comparing

- "Homemade" with a regular db, represented by mongodb in this project
- Using Apache Kafka with unlimited retention time/size as an event store
- Using Event Store from eventstore.org

Usage:
Start docker containers for all three persistence applications.
```
docker run --name mongodb -v mongodata:/data/db -d -p 27017:27017 mongo
docker run -p 9092:9092 -e ADVERTISED_HOST=localhost johnnypark/kafka-zookeeper 
docker run --name eventstore-node -it -p 2113:2113 -p 1113:1113 eventstore/eventstore
```

Start the application with ``mvn spring-boot:run``.
