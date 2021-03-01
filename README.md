# Fuel-Cost-Calculator

Steps to Run the Application

1. Go to mocked-service folder and then change directory to fpm-ety and then run command **gradle clean install**.

2. Go to mocked-service directory and run command **gradle clean build docker** to build docker image.

3. Go to event-producer folder and then change directory to fpm-ety and then run command **gradle clean install**.

4. Go to event-producer directory and run command  **gradle clean build docker** to build docker image.

5. Go to fuel-cost-calculator and run command **gradle clean build docker** to build docker image.

6. There is a docker-compose file in the folder run command **docker-compose -f Fcc-docker-compose.yml up -d**.

7. Open any mongo-client and take username and password from compose file to verify data in mongodb.

8. run command docker exec -it kafka_1_container(kakfa-1 container name) bash
   Run below command to see list of kafka topics  
   kafka-topics --list --bootstrap-server localhost:19092

   Run below command to verify the data in kafka topics
   kafka-console-consumer --list --bootstrap-server localhost:19092 --topic business_event --from-beginning
