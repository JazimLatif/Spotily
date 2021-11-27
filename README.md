# Spotily

## Project Summary

Music recommendation service built with PostgreSQL, Spring and Java. Answer a few simple questions and get a playlist of songs based on your answers!

### MVP
Our minimum viable product was a service where users could submit a quiz and have a relevant playlist created for them based on their answers, attached to their user id and stored in our database. 

### Extensions 
We also added several extensions:  
* An admin property for users, where only admins could perform certain actions such as adding new songs and questions to the database  
* A theme property for questions and songs, so users could choose to answer a quiz or get songs based around a certain topic  
* A switch song option, where a user could change playlist songs they disliked, for new ones that still matched the quiz answers they gave

## How to Set Up and Use

To install the application, clone the repository and run the project or the DemoApplication class in your IDE. The project must be connected to a local database, and the name of this database should be given in the application.properties file, alongside any username and password for it. This should be a new, empty database. The application properties file currently has the database name as 'testspotily', so it may be easiest to set up a database with the same name. 
The database will be populated with some starter data upon running, as some table entries are provided in the db/migration folder of the repository.   

## API Endpoints  

* POST /api/user/register – adds a new non-admin user, takes a user object in the request body
* POST /api/user/register/admin – adds a new admin user, takes a user object in the request body
* DELETE /api/user/delete/{id} – deletes the user with the given id
* PUT /api/user/update/{id} – updates the user with the given id, takes a user object in the request body

* GET /playlist – gives all playlists from the database
* GET /playlist/{id} – shows the playlist with the given id
* DELETE /playlist/{id} – deletes the playlist with the given id
* POST /playlist/{playlistid}/{songid} - replaces the song with given id in the selected playlist

* GET /api/quiz/getquiz – gives a random quiz object
* DELETE /api/quiz/delete/{userid}/{questionid} – deletes a question if the user id is an admin
* POST /api/quiz/submit-return – creates a playlist in the database and returns it
* POST /api/quiz/{id}/submit-question – adds a new question if the user id is an admin, takes a question object in the request body

* GET /showSongs/{adminId} – shows all song data, given a valid admin user id
* POST /addSong/{adminId} – adds a new song, given a valid admin user id
* DELETE /delete/{adminId}/{songId} – deletes the selected song, given a valid admin user id
* PUT /update/{adminId}/{songId} – updates the selected song, given a valid admin user id

## JSON Object Examples

For sending information in the request body to the database, the following JSON structures can be used.

User:
```
{
  "username" : "musicliker666",
  "email" : "musicliker@email.com",
  "admin" : false
}
```

Quiz:
```
{
  "userId": 1,
  "questionsAndOptions": {
    "What is the best season?": [
      "Autumn",
      "Winter",
      "Spring",
      "Summer"
    ],
    "are you a ___ type of person": [
      "early riser",
      "sleep until noon",
      "in bed all day",
      "unwillingly dragged out of bed"
    ],
    "which one would you rather be doing right now?": [
      "dancing",
      "reading",
      "crying",
      "fighting someone"
    ],
    "Which of these is your favourite mode of transport?": [
      "Bicycle",
      "Bus",
      "Motorbike",
      "Walking"
    ]
  },
  "answers": [
    "Autumn",
    "early riser",
    "crying",
    "Walking"
  ]
}
```

Question:
```
{
  "text": "What is your favourite Christmas activity?",  
  "optionsAndMoods" : {
    "Family games": "happy",
      "Snowball fight": "angry",
      "Thinking about how fast the year went": "sad",
      "Falling asleep on the sofa": "relaxed"
  },
  "theme": null
}
```

Song:
```
{
  "mood" : "happy",
  "songName" : "The Happy Song",
  "artist" : "The Happy Musician",
  "theme" : null
}
```


## Credits

This project was created as part of the Bright Network Technology Academy full-stack bootcamp.  

Contributors:  
Jazim (@JazimLatif)  
Nagina (@nagiinaaa)  
Jason (@jsum20)  
Chaam (@CZboop & @Branchey)
