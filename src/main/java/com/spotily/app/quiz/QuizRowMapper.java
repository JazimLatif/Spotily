package com.spotily.app.quiz;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//public class QuizRowMapper implements RowMapper<Quiz> {
//
//    @Override
//    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Quiz option = new Quiz(Optional.empty(),
//                rs.getString("question_text"),
//                new ArrayList<>(List.of(rs.getString("option_text"))),
//                Optional.empty()
//        );
//        return option;
////        again needs a plan about how to pass the rowmapper the userid
////        add a quiz id to the user_answers table to help w this?
//
//    }
//
//}
//private Optional<Integer> userId;
//    private String question;
//    private ArrayList<String> options;
//    private Optional<String> answer;
