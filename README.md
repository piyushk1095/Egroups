# Applicant Test Task: Design of a REST API
***

## Prerequisite  :
 1. mySql
 2. Eclipse
 3. Postman
***
## To run the project :
### Database
***
Run the following query to create table name `stories`
```
CREATE TABLE `stories` (
  `issueID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `creationDate` date DEFAULT NULL,
  `assignedDev` varchar(45) DEFAULT NULL,
  `estimatedPoints` int DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`issueID`)
);

```
***
Run the following query to create table name `developer`
```
CREATE TABLE `developer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

```
***
Run the following query to create table name `bugs`

```
CREATE TABLE `bugs` (
  `issueID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `desCreationDate` varchar(45) NOT NULL,
  `asignDev` varchar(45) NOT NULL,
  `priority` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`issueID`)
);

```

### Backend:

*** 
Add mysql database credentals to [Egroups/Trackers/src/main/resources/application.properties]

To run the application run the file `Application.java` as java application [Egroups/Trackers/src/main/java/com/demo/Application.java]


### JSON files:

*** 

Developers to add as `POST` http://localhost:8080/dev 
```
{
    "name": "dev1"
}
```

***


Stories to add use postman as `POST` as use url http://localhost:8080/stories 
```
{
    "title": "New Jira Created",
    "creationDate": "2021-10-02T23:59:51+01:00",
    "estimatedPoints": 18,
    "status": "NEW"
}
```
***

To get list of Stories use postmas as GET and use url `http://localhost:8080/stories`

***

To get list of Developers use postmas as GET and use url `http://localhost:8080/dev`
