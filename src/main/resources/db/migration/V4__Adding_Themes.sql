CREATE TABLE themes (id SERIAL PRIMARY KEY, tag VARCHAR(255));

ALTER TABLE songs ADD song_theme INT REFERENCES themes(id);

ALTER TABLE questions ADD question_theme INT REFERENCES themes(id);