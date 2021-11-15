package com.spotily.app.quiz;

import java.util.*;

//the quiz object will represent a complete quiz
public class Quiz {

//    this class now represents an individual question, and the quiz will just be a collection of these
//    optional userid, don't need it to get questions, can make it needed or not to submit
    private Optional<Integer> userId;
//    optional
    private HashMap<String, ArrayList<String>> questionsAndOptions;
//    private ArrayList<String> options;
//    check whether each answer is null at the point of submit
    private ArrayList<Optional<String>> answers;


    public Quiz(Optional<Integer> userId, HashMap<String, ArrayList<String>> questionsAndOptions, ArrayList<Optional<String>> answers) {
        this.userId = userId;
        this.questionsAndOptions = questionsAndOptions;
        this.answers = answers;
    }

    public Optional<Integer> getUserId() {
        return userId;
    }

    public HashMap<String, ArrayList<String>> getQuestionsAndOptions() {
        return questionsAndOptions;
    }

    public ArrayList<Optional<String>> getAnswers() {
        return answers;
    }

    public void setUserId(Optional<Integer> userId) {
        this.userId = userId;
    }

    public void setQuestionsAndOptions(HashMap<String, ArrayList<String>> questionsAndOptions) {
        this.questionsAndOptions = questionsAndOptions;
    }

    public void setAnswers(ArrayList<Optional<String>> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(userId, quiz.userId) && Objects.equals(questionsAndOptions, quiz.questionsAndOptions) && Objects.equals(answers, quiz.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, questionsAndOptions, answers);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "userId=" + userId +
                ", questionsAndOptions=" + questionsAndOptions +
                ", answers=" + answers +
                '}';
    }
}
