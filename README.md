<p align="center">
    <h1 align="center">LockedraVault</h1>
</p>
<p align="center">
		<em>Developed with the software and tools below.</em>
</p>
<p align="center">
	<img src="https://img.shields.io/badge/Java-white?style=flat&color=grey" alt="Java">
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=springboot&logoColor=black&color=green" alt="Spring Boot">
	<img src="https://img.shields.io/badge/SQLite-4169E1?style=flat&logo=sqlite&logoColor=white" alt="SQLite">
	<img src="https://img.shields.io/badge/JUnit5-25A162?style=flat&logo=junit5&logoColor=white" alt="JUnit5">
	<img src="https://img.shields.io/badge/Mockito-8D6748?style=flat&logo=mocha&logoColor=white" alt="Mockito">
</p>
<hr>

##  Overview

This project was made for learning purposes with new features currently under development.

This project aimed to develop a desktop application allowing users to store passwords in a secure way. 
Utilized client - server architecture with Java Spring Boot for the backend, SQLite for data storage. The client will be built with electron, currently under planning phase.
Despite the client - server architecture, the application store passwords in a decentralised way, therefore both the server and the client are running locally on the user's device.

## Setup
***- Before you begin, ensure you have met the following requirements:***

- Java Development Kit (JDK) installed (version 17.0.8)

- Maven build tool installed (version 3.9.2)

- SQL database (SQLite) installed

- IDE (e.g., IntelliJ IDEA) installed for development (optional but recommended)

**Steps**

***- Clone the Repository***
```
git clone git@github.com:Marcel-zb96/LockedraVault.git
```

***- Build the Application***
```
mvn clean install
```
***- Run the Application***
```
java -jar target/LockedraCault-<version>-SNAPSHOT.jar
```
Replace <version> with the version of your application.
