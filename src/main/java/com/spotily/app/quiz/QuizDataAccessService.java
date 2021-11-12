package com.spotily.app.quiz;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository("postgres")
public class QuizDataAccessService {

    private JdbcTemplate jdbcTemplate;

    public QuizDataAccessService (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean submitQuiz(Quiz quiz){
//        sql logic to add to db - completed quiz by user
        return true;
    }

     public int updateQuiz(Map<String, String> questionsAndAnswers){
        return 0;
     }

     public Quiz getQuizById(int id){
// note this is for getting a quiz that user has completed, to pass to the playlist maker
//         so will need some extra work and validation
//         have the user id feed into this, will be called from the point of submit so..
         String sql = """
                 SELECT questions, user_answers
                 FROM questions INNER JOIN ON 
                 questions.id = options.question_id
                 WHERE questions.quiz_id = ?
                 """;
//         above sql needs work
         HashMap<String, Optional<String>> quizMap = new HashMap<String, Optional<String>>();
//         use sql info to add to hashmap...
//         need to get userid from sql
         return new Quiz(id, 0, quizMap);
     }

    public Quiz getQuizInfoById(int id){
// note this is for getting the quiz just to display questions
        String sql = """
                 SELECT questions, options 
                 FROM questions INNER JOIN options ON 
                 questions.id = options.question_id
                 WHERE questions.quiz_id = ?
                 """;
//        add logic to turn the query into a format that can be shown as the quiz questions
//        probably not the existing quiz object, data types won't allow unless comma/some char separated options or something
                HashMap<String, Optional<String>> quizMap = new HashMap<String, Optional<String>>();
//         use sql info to add to hashmap...
        return new Quiz(0, 0, quizMap);
    }


}
