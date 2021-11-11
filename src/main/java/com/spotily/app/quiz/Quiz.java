package com.spotily.app.quiz;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

//the quiz object will represent a complete quiz
public class Quiz {

    private int id;
    private int userId;
    private HashMap<String, Optional<String>> questionsAndAnswers;


    public Quiz(int id, int userId, HashMap<String, Optional<String>> questionsAndAnswers) {
        this.id = id;
        this.userId = userId;
        this.questionsAndAnswers = questionsAndAnswers;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public HashMap<String, Optional<String>> getQuestionsAndAnswers() {
        return questionsAndAnswers;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setQuestionsAndAnswers(HashMap<String, Optional<String>> questionsAndAnswers) {
        this.questionsAndAnswers = questionsAndAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return id == quiz.id && userId == quiz.userId && Objects.equals(questionsAndAnswers, quiz.questionsAndAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, questionsAndAnswers);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", userId=" + userId +
                ", questionsAndAnswers=" + questionsAndAnswers +
                '}';
    }
}
