CREATE TABLE songs (id SERIAL PRIMARY KEY, mood VARCHAR(255), artist VARCHAR(255), song_name VARCHAR(255));

CREATE TABLE users (id SERIAL PRIMARY KEY, username VARCHAR(255), email VARCHAR(255) );

CREATE TABLE playlist (id SERIAL PRIMARY KEY, playlist_user INT REFERENCES users(id));

CREATE TABLE playlist_maker (id SERIAL PRIMARY KEY, playlist_id INT REFERENCES playlist(id),
 song_id INT REFERENCES songs(id));

CREATE TABLE questions (id SERIAL PRIMARY KEY, question_text VARCHAR(255));

CREATE TABLE options (id SERIAL PRIMARY KEY, question_id INT REFERENCES questions(id),
 option_text VARCHAR(255), option_mood VARCHAR(255));

/*
CREATE TABLE user_answers (id SERIAL PRIMARY KEY, user_id INT REFERENCES users(id),
 question_id INT REFERENCES questions(id), option_id INT REFERENCES options(id));

 CREATE TABLE quizzes (id SERIAL PRIMARY KEY, title VARCHAR(255));

*/

/*
Maybe need an inner join between song and options linked via mood
Might not need quiz and user_answers because can just query between other tables
*/
