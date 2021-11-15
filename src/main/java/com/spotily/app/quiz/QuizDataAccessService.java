package com.spotily.app.quiz;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
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

//     change this to a get random question and have the quiz service do putting the quiz together
//    return and return type will change
     public ArrayList<String> getQuestionOptions(String question){
// no longer having quizzes in database, just building out of questions

//         below query gets the question text and options for a random question, but better to split up probs
         String sql = """
                 SELECT option_text 
                 FROM options JOIN questions ON questions.id = options.question_id 
                 WHERE question_text = ?;
                 """;

         List<Map<String, Object>> options_list = jdbcTemplate.queryForList(sql, question);
//         this standard kind of query returns the column heading and value as key value in map list
//         so can extract however based on that
         ArrayList<String> options = new ArrayList<>();
         for (Map elem: options_list){
             options.add((String) elem.values().toArray()[0]);
         }
//         above gets all the values but assumes the data is the way given from current sql query - single column
         return options;
     }

     public String getRandomQuestion(){
        String sql = """
                SELECT question_text FROM questions ORDER BY RANDOM () LIMIT 1;
                """;
        String question = (String) jdbcTemplate.queryForObject(sql, new Object[] {}, String.class);
        return question;
     }

//    public Quiz getQuizInfoById(int id){
//// note this is for getting the quiz just to display questions
//        String sql = """
//                 SELECT questions, options
//                 FROM questions INNER JOIN options ON
//                 questions.id = options.question_id
//                 WHERE questions.quiz_id = ?
//                 """;
////        add logic to turn the query into a format that can be shown as the quiz questions
////        probably not the existing quiz object, data types won't allow unless comma/some char separated options or something
////         use sql info to add to hashmap...
//        return ;
//    }


}

//     public Quiz getQuizById(int id){
//// note this is for getting a quiz that user has completed, to pass to the playlist maker
////         so will need some extra work and validation
////         have the user id feed into this, will be called from the point of submit so..
//         String sql = """
//                 SELECT questions, user_answers
//                 FROM questions INNER JOIN ON
//                 questions.id = options.question_id
//                 WHERE questions.quiz_id = ?
//                 """;
////         above sql needs work
//         HashMap<String, Optional<String>> quizMap = new HashMap<String, Optional<String>>();
////         use sql info to add to hashmap...
////         need to get userid from sql
//         return new Quiz(id, 0, quizMap);
//     }
