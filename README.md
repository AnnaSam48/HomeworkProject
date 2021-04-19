# HomeworkProject

This is small coding exercise with goal of challenging myself as well as showing my abilities as a programmer.

### Requirements:
Create a REST services that would support the following functionality:

#### Customer CRUD functionality (create, read, update, delete):
*	user submits: name, surname, country, email, password
*	should be validated on server side for email’s correct format, non-empty values for other fields like name, surname, country, password
#### Customer debt case CRUD functionality (create, read, update, delete)
*	user submits amount, currency, due date
*	the debt case should be linked to existing customer
*	should be validated on server side for positive amount, correct date, and existing customer
#### Data should be stored in database
*	Application should be covered by unit and integration tests

### Prerequisites

This aplication needs latest maven version installed.

## General info:
Aplication makes use of Spring HATEOAS and H2 data base.

## Can be accessed from: *http://localhost:8020/cases* or *http://localhost:8020/customers*

(the port also can be configured in properties file if needed) 
Access data username, password can be found in properties file.

There are 2 json files available in HomeworkProject\data\json_models for Customer and DebtCase respectively, that can be used and modified as necessary.
Also there is a sql script for some data to be loaded in database (you need to uncomment it and then execute) src/main/resources/Data.sql.

Application is based on REST architectural principles for lose coupling (hateoas, DTOs).

//TODOS Still necessary:

*Lots of testing (only services have been tested)
*Repository configuration modifications (Spring currently is not supporting DELETE method call and gives 405 error).
*Error message costumization (in FE)
*Unique customer check.

