# Simple Round Robin Load Balancer

This repository contains a simple implementation of a load balancer using the Round Robin algorithm. The load balancer is built using Java 17 and Spring Boot.

## Overview

This project demonstrates a basic load balancer that distributes incoming requests across multiple backend servers. It uses the Round Robin algorithm to sequentially route requests to each server in a circular manner.

## Components

### Load Balancer Controller

The `LoadBalancerController` class defines the REST endpoint for incoming requests and implements the logic for load balancing. Requests are forwarded to backend servers based on the Round Robin algorithm.

### Round Robin Algorithm

The Round Robin algorithm is a simple method for distributing requests evenly across a set of backend servers. It works by sequentially cycling through the list of servers and routing each incoming request to the next server in the list.


The load balancer will forward the request to the backend servers in a round-robin fashion and return the response.

## Configuration

By default, the load balancer is configured to use two backend servers running on ports 8081 and 8082. You can modify the `backendUrls` list in the `LoadBalancerController` constructor to include additional backend servers or change their URLs.

## Usage

1. Ensure you have Java 17 and Maven installed on your system.
2. Clone this repository:

#!/bin/bash

1- Clone the repository
 

 ```bash
    git clone <repository-url>
 ```

2- Navigate to the project directory

```bash
    cd Load-Balencer
```

3- Build the project
```bash
    mvn clean install
```
4- Run the application 

5- Send requests to the load balancer:

```bash
curl http://localhost:8080/
```
