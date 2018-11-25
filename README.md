# KumuluzEE OpenTracing Sample

> Simple example on how to use KumuluzEE OpenTracing extension.

In this example we have 2 microservices (customers and orders). 
Example demonstrates how to take advantage of distributed tracing 
in KumuluzEE projects.

## Requirements

In order to run this example you will need the following:

1. Java 8 (or newer), you can use any implementation:
    * If you have installed Java, you can check the version by typing the following in a command line:
        
        ```bash
        $ java -version
        ```

2. Maven 3.2.1 (or newer):
    * If you have installed Maven, you can check the version by typing the following in a command line:
        
        ```bash
        $ mvn -version
        ```
        
3. Docker 1.13.0 (or newer):
    * If you have installed Docker, you can check the version by typing the following in a command line:
    
        ```bash
        $ docker --version
        ```


## Prerequisites

Make sure you have Jaeger tracing instance running.

```bash
$ docker run -d -p 5775:5775/udp -p 16686:16686 jaegertracing/all-in-one:latest
```

## Usage

The example uses docker to build and run the microservices.

1. Build the sample using maven:

    ```bash
    $ cd kumuluzee-opentracing-sample
    $ mvn clean package
    ```
    
2. Run each individual microservice separately (separate terminal):
    
    ```bash
    $ PORT=3000 java -jar customers/target/opentracing-customers-1.0.0-SNAPSHOT.jar
    $ PORT=3001 java -jar orders/target/opentracing-orders-1.0.0-SNAPSHOT.jar
    ```
    
3. Navigate to <http://localhost:3000/v1/customers/1/orders>

4. View traces in Jaeger console <http://localhost:16686>