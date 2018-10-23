# README

## Starting the application
The application can be started in a couple of different ways:

via maven: 
* mvn spring-boot:run

via tomcat:
* build war: mvn clean package
* deploy on tomcat

## Requirements

The application requires a running MySQL instance, that can be configured in the application.yml. The current standard expects:

* port: 3306
* username: root
* password: 
* database: person_service
