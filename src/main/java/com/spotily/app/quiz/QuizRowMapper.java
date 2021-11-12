package com.spotily.app.quiz;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class QuizRowMapper implements RowMapper<Quiz> {

    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz(
                rs.getInt("id"),
//                *****
                rs.getInt(""),
                new HashMap<String, Optional<String>>()

        );
        return quiz;
//        again needs a plan about how to pass the rowmapper the userid
//        add a quiz id to the user_answers table to help w this?

    }

}
//private int id;
//    private int userId;
//    private HashMap<String, Optional<String>> questionsAndAnswers;
