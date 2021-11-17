INSERT INTO questions(question_text) VALUES ('where would you rather be right now?');
INSERT INTO options (question_id, option_text, option_mood) VALUES (1, 'a party', 'happy');
INSERT INTO options (question_id, option_text, option_mood) VALUES (1, 'the beach', 'relaxed');
INSERT INTO options (question_id, option_text, option_mood) VALUES (1, 'in bed', 'sad');
INSERT INTO options (question_id, option_text, option_mood) VALUES (1, 'a boxing ring', 'angry');

INSERT INTO questions(question_text) VALUES ('pick a pet?');
INSERT INTO options (question_id, option_text, option_mood) VALUES (2, 'dog', 'happy');
INSERT INTO options (question_id, option_text, option_mood) VALUES (2, 'cat', 'relaxed');
INSERT INTO options (question_id, option_text, option_mood) VALUES (2, 'fish', 'sad');
INSERT INTO options (question_id, option_text, option_mood) VALUES (2, 'lion', 'angry');

INSERT INTO questions(question_text) VALUES ('which one would you rather be doing right now?');
INSERT INTO options (question_id, option_text, option_mood) VALUES (3, 'dancing', 'happy');
INSERT INTO options (question_id, option_text, option_mood) VALUES (3, 'reading', 'relaxed');
INSERT INTO options (question_id, option_text, option_mood) VALUES (3, 'crying', 'sad');
INSERT INTO options (question_id, option_text, option_mood) VALUES (3, 'fighting someone', 'angry');

INSERT INTO questions(question_text) VALUES ('are you a ___ type of person');
INSERT INTO options (question_id, option_text, option_mood) VALUES (4, 'early riser', 'happy');
INSERT INTO options (question_id, option_text, option_mood) VALUES (4, 'sleep until noon', 'relaxed');
INSERT INTO options (question_id, option_text, option_mood) VALUES (4, 'in bed all day', 'sad');
INSERT INTO options (question_id, option_text, option_mood) VALUES (4, 'unwillingly dragged out of bed', 'angry');

INSERT INTO questions(question_text) VALUES ('what is your current mood?');
INSERT INTO options (question_id, option_text, option_mood) VALUES (5, 'happy', 'happy');
INSERT INTO options (question_id, option_text, option_mood) VALUES (5, 'relaxed', 'relaxed');
INSERT INTO options (question_id, option_text, option_mood) VALUES (5, 'sad', 'sad');
INSERT INTO options (question_id, option_text, option_mood) VALUES (5, 'angry', 'angry');

INSERT INTO questions(question_text) VALUES ('what is your favourite ice cream flavour?');
INSERT INTO options (question_id, option_text, option_mood) VALUES (6, 'strawberry', 'happy');
INSERT INTO options (question_id, option_text, option_mood) VALUES (6, 'vanilla', 'relaxed');
INSERT INTO options (question_id, option_text, option_mood) VALUES (6, 'chocolate', 'sad');
INSERT INTO options (question_id, option_text, option_mood) VALUES (6, 'mint chocolate', 'angry');

INSERT INTO songs (mood, artist, song_name) VALUES('happy', 'pharrell williams', 'happy');
INSERT INTO songs (mood, artist, song_name) VALUES('relaxed', 'hozier', 'take me to church');
INSERT INTO songs (mood, artist, song_name) VALUES('angry','blackbear', 'do re mi' );
INSERT INTO songs (mood, artist, song_name) VALUES('angry', 'fall out boy', 'thnks fr th mmrs');
INSERT INTO songs (mood, artist, song_name) VALUES('sad', 'jacob banks', 'mercy');
INSERT INTO songs (mood, artist, song_name) VALUES('sad', 'rag n bone man', 'skin');
INSERT INTO songs (mood, artist, song_name) VALUES('angry', 'miley cyrus', 'wtf do i know');
INSERT INTO songs (mood, artist, song_name) VALUES('happy', 'bruno mars', 'uptown funk');
INSERT INTO songs (mood, artist, song_name) VALUES('relaxed', 'artic monkey', 'do i wanna know?');
INSERT INTO songs (mood, artist, song_name) VALUES('happy', 'imagine dragons', 'on top of the world');
