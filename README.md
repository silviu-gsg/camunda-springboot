# Camunda Onboarding

## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Building the application](#building-the-application)
4. [Running the application](#running-the-application)
5. [Notes](#notes)

### General Info
Camunda Onboarding is a demo project which uses the Camunda BPM Engine to implement a generic onboarding process which aids users in creating their account remotely on a given platform

### Technologies
The following main technologies were used:
* [Camunda BPM](https://docs.camunda.org/manual/7.14/): Version 7.14 
* [Spring Boot](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/): Version 2.3.1.RELEASE
* [MyBatis](https://mybatis.org/mybatis-3/): Version 3.5.3

### Building the application

The project uses [Gradle](https://gradle.org) as a build tool. It already contains
`./gradlew` wrapper script, so there's no need to install gradle.

To build the project execute the following command:

```bash
  ./gradlew build
```

### Running the application

Just run the jar using:

```bash
java -jar .\build\libs\camunda-onboarding-0.0.1-SNAPSHOT.jar
```
It will start the Camunda Engine and use the embedded in-memory H2 database.

After the server is running, go to:

```
http://localhost:8080/camunda/app/
```
and use the following credentials:
```
Username: kermit
Password: kermit 
```

### Notes

* You can explore the modeled BPM process with any BPM viewer, even with the Camunda Modeler.
Just open the process configuration found here:
    ```
    src/main/resources/onboarding-process.bpmn
    ```
    Alternatively, you can view the process directly in the Camunda UI by accessing:
    ````
    Login -> Cockpit -> Process definitions -> onboarding-process
    ````
* MyBatis is used for the sample custom REST API that can return data aggregations which may not be  available through the standard Camunda REST API.
This persistence framework is also used by Camunda internally and is the preferred way of aggregating persisted data via SQL queries.