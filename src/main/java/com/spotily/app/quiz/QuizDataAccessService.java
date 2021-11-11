package com.spotily.app.quiz;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class QuizDataAccessService {

    private JdbcTemplate jdbcTemplate;

    public QuizDataAccessService (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean submitQuiz(Quiz quiz){
//        sql logic to add to db
        return true;
    }

     public int updateQuiz(Map<String, String> questionsAndAnswers){
        return 0;
     }

     public Quiz getQuizById(int id){
//        sql select to get the quiz and associated answers and options - will we need another object for this?
//         or just a data structure/ quiz with map with nulls
//         quiz with null answers probably
         HashMap<String, Optional<String>> quizMap = new HashMap<String, Optional<String>>();
//         use sql info to add to hashmap...
         return new Quiz(0, 0, quizMap);
     }



}
