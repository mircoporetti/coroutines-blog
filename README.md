<h2>Coroutines Blog</h2>

A very simple REST service using Kotlin Coroutines.

This is a Kotlin/Coroutines version of the project ["Reactive Blog"](https://github.com/mircoporetti/reactive-blog).

In this example I will use RestAssured for integration testing and MongoDB for the persistence.

The app is structured inspiring by Clean Architecture and Hexagonal Architecture.

<h3>Requirements</h3>

- Java 17
- Docker

<h3>How to run it?</h3>

Compile everything:

    ./gradlew build

Create the database and run the application:

    docker compose up   
