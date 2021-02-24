# Camunda Origination

## Description
Camunda Origination is a demo project which uses the Camunda BPM Engine to implement a generic origination business process where users can perform their onboarding on any platform

## Building and running the application

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
java -jar .\build\libs\camunda-origination-0.0.1-SNAPSHOT.jar
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

## Notes

You can explore the modeled BPM process with any BPM viewer, even with the Camunda Modeler.
Just open the process configuration found here:
```
src/main/resources/origination-process.bpmn
```
Alternatively, you can view the process directly in the Camunda UI by accessing:
````
Login -> Cockpit -> Process definitions -> origination-process
````