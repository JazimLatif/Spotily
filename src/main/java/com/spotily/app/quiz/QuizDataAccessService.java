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

     public ArrayList<String> getQuestionOptions(String question){
         String sql = """
                 SELECT option_text 
                 FROM options JOIN questions ON questions.id = options.question_id 
                 WHERE question_text = ?;
                 """;

         List<Map<String, Object>> options_list = jdbcTemplate.queryForList(sql, question);

         ArrayList<String> options = new ArrayList<>();
         for (Map elem: options_list){
             options.add((String) elem.values().toArray()[0]);
         }
         return options;
     }

     public String getRandomQuestion(){
        String sql = """
                SELECT question_text FROM questions ORDER BY RANDOM () LIMIT 1;
                """;
        String question = (String) jdbcTemplate.queryForObject(sql, new Object[] {}, String.class);
        return question;
     }

    public String getThemedQuestion(int theme){
        String sql = """
                SELECT question_text FROM questions WHERE question_theme = ? ORDER BY RANDOM () LIMIT 1;
                """;
        String question = (String) jdbcTemplate.queryForObject(sql, new Object[] {theme}, String.class);
        return question;
    }

     public int getNewQuestionId(){
         String sql = """
                SELECT questions.id FROM questions ORDER BY questions.id DESC LIMIT 1;
                """;
         int questionId = (int) jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
         return questionId;
     }

     public int addQuestion(String question){
        String sql = """
                INSERT INTO 
                questions (question_text) 
                VALUES (?);
                """;
        return jdbcTemplate.update(sql, question);
     }

    public int addThemedQuestion(String question, Integer theme){
        String sql = """
                INSERT INTO 
                questions (question_text, question_theme) 
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(sql, question, theme);
    }

    public ArrayList<Integer> getAdmin() {
        String sql = """
                SELECT users.id
                FROM users 
                WHERE users.admin = 'true';
                """;
        return (ArrayList<Integer>) jdbcTemplate.query(sql, new QuizResultSetExtractor());
    }

    public int addOption(int questionId, String option, String mood){
        String sql = """
                INSERT INTO 
                options (question_id, option_text, option_mood) 
                VALUES (?, ?, ?);
                """;
        return jdbcTemplate.update(sql, questionId, option, mood);
    }

}

