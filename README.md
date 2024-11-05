# ChargingStationService
1 **Technologies used:**
Java 21
Spring Boot
KStreams
Microservices

**Kafka Topics**:
**charging-status**: ChargingUpdateService will continuously publish messages to this topic for all the requests.
**charging-status-stream**: ChargingUpdateService will read data from above topic and publishes updated message using KStream to this topic. 
                            ChargingProcessorService consumes messages from this topic and updates required details in database.

**Services Will be available on locahost on following ports:**

Start the services as per port details given below:

Zookeeper: 2181
Broker-1: 9092
Broker-2: 9093
Kafka-ui: 8081
ChargingStationService: 8056
ChargingUpdateService: 8052
ChargingProcessorService: 8053
