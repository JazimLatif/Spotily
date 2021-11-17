package com.spotily.app.quiz;

import java.util.ArrayList;
import java.util.Optional;

public interface QuizDAO {
    ArrayList<String> getQuestionOptions(String question);
    Optional<String> getRandomQuestion();
    Optional<String> getThemedQuestion(int theme);
    Optional<Integer> getNewQuestionId();
    int addQuestion(String question);
    int addThemedQuestion(String question, Integer theme);
    ArrayList<Integer> getAdmin();
    int addOption(int questionId, String option, String mood);
    ArrayList<Integer> getAllQuestionIds();
    int updateQuestion(String questionText, int questionId);
    int deleteOptionsByQuestionId(int questionId);
    int deleteQuestionById(int questionId);
}
