package com.spotily.app.quiz;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class Question {
    private String text;
    private HashMap<String, String> optionsAndMoods;
    private Optional<Integer> theme;

    public Question(String text, HashMap<String, String> optionsAndMoods, Optional<Integer> theme) {
        this.text = text;
        this.optionsAndMoods = optionsAndMoods;
        this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HashMap<String, String> getOptionsAndMoods() {
        return optionsAndMoods;
    }

    public void setOptionsAndMoods(HashMap<String, String> optionsAndMoods) {
        this.optionsAndMoods = optionsAndMoods;
    }

    public Optional<Integer> getTheme() {
        return theme;
    }

    public void setTheme(Optional<Integer> theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(text, question.text) && Objects.equals(optionsAndMoods, question.optionsAndMoods) && Objects.equals(theme, question.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, optionsAndMoods, theme);
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", optionsAndMoods=" + optionsAndMoods +
                ", theme=" + theme +
                '}';
    }
}
