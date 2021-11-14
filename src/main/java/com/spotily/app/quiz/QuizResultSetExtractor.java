package com.spotily.app.quiz;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

//public class QuizResultSetExtractor implements ResultSetExtractor {
//
//    @Override
//    public Quiz extractData(ResultSet rs) throws SQLException,
//            DataAccessException {
//        Quiz question = new Quiz(
//                Optional.empty(),
//                "",
//                new ArrayList<String>(),
//                Optional.empty()
//        );
//        while (rs.next()){
//            question.setQuestion(rs.getString("question_text"));
//            ArrayList<String> qOptions = question.getOptions();
//            qOptions.add(rs.getString("option_text"));
//            question.setOptions(qOptions);
//        }
//        return question;
//    }
//}
////private Optional<Integer> userId;
////    private String question;
////    private ArrayList<String> options;
////    private Optional<String> answer;
