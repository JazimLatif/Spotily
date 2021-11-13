package com.spotily.app.quiz;

import com.spotily.app.playlist.PlaylistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private QuizDataAccessService quizDataAccessService;
    private PlaylistService playlistService;

    public void submitQuiz(Quiz quiz){
        boolean success = quizDataAccessService.submitQuiz(quiz);
        if (success){
            evaluateQuiz(quiz.getId());
        }
    }

    public Quiz getQuizQuestions(int id){
//        maybe more logic to have this just show the questions, should be fine for now
        return quizDataAccessService.getQuizById(id);
    }

    public Quiz getQuizById(int id){
        return quizDataAccessService.getQuizById(id);
    }

    public void evaluateQuiz(int id){
//        trigger this once a quiz is submitted, have it get data from responses?
        Quiz quiz = getQuizById(id);
        ArrayList<Optional<String>> answers = new ArrayList<>();
        HashMap<String, Optional<String>> qAndAMap = quiz.getQuestionsAndAnswers();
        for (String key: qAndAMap.keySet()){
            answers.add(qAndAMap.get(key));
        }
//        some logic to get most common answer/distribution or filter answers etc
//        get the non-null values from optionals
        ArrayList<String> nonNulls = new ArrayList<>(answers.stream().filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList()));
//        call the playlist method that takes this and turns into playlist
//        check nonnulls has some values first else some error
        if (nonNulls.size()!=0){
            playlistService.makePlaylist(nonNulls, quiz.getUserId());
        }
//        return nonNulls;
    }



}
