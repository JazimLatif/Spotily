# Spotily

## Project Summary

Music recommendation service built with PostgreSQL, Spring and Java. Answer a few simple questions and get a playlist of songs based on your answers!

### MVP
Our minimum viable product was a service where users could submit a quiz and have a relevant playlist created for them based on their answers, attached to their user id and stored in our database. 

### Extensions 
We also added several extensions:  
* An admin property for users, where only admins could perform certain actions such as adding new songs and questions data to the database  
* A theme property for questions and songs, so users could choose to answer a quiz or get songs based around a certain topic  
* A switch song option, where a user could change playlist songs they disliked, for new ones that still matched the quiz answers they gave

## How to Set Up and Use

To install the application, clone the repository and run the project or the DemoApplication class in your IDE. The project must be connected to a local database, and the name of this database should be given in the application.properties file, alongside any username and password for it. This should be a new, empty database.  
The database will be populated with some starter data upon running, as some table entries are provided in the db/migration folder of the repository.   


## Credits

This project was created as part of the Bright Network Technology Academy full-stack bootcamp.  

Contributors:  
Jazim (@JazimLatif)  
Nagina (@nagiinaaa)  
Jason (@jsum20)  
Chaam (@CZboop & @Branchey)
