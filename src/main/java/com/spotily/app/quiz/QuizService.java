package com.spotily.app.quiz;

import com.spotily.app.playlist.PlaylistDataAccessService;
import com.spotily.app.playlist.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spotily.app.exception.ResourceNotFound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private QuizDataAccessService quizDataAccessService;
    private PlaylistService playlistService;



    @Autowired
    public QuizService(PlaylistService playlistService, QuizDataAccessService quizDataAccessService) {
        this.playlistService = playlistService;
        this.quizDataAccessService = quizDataAccessService;
    }


    public String getRandomQ(){
        return quizDataAccessService.getRandomQuestion();
    }

    public ArrayList<String> getQOptions(String question){
        return quizDataAccessService.getQuestionOptions(question);
    }

//    just fiddly to either make new collections or add to existing,
//    also might have to adjust method to take input/think some more lol
//    probs another method, this still good to match question and options
    public HashMap<String, ArrayList<String>> makeRandomQuestionOptionsMap(){
        String randQ = getRandomQ();
        ArrayList<String> matchingOptions = getQOptions(randQ);
        HashMap options_map = new HashMap<String, ArrayList<String>>();
        options_map.put(randQ, matchingOptions);
//        Quiz fullQ = new Quiz(Optional.empty(), options_map, newOptional.empty());
        return options_map;
//        Optional<Integer> userId, HashMap<String, ArrayList<String>> questionsAndOptions, ArrayList<Optional<String>> answers
    }

//    could have number of questions be a argument for the function? if want to vary
    public Quiz makeEmptyQuiz(){
        Quiz fullQuiz = new Quiz(Optional.empty(), new HashMap<>(), new ArrayList<>());

        if (fullQuiz == null){
            throw new ResourceNotFound("Error: quiz couldn't be created");

        } else {
            for (int i = 0; i < 5; i++) {
//            add the option map from method to the quiz hashmap
//                HashMap<String, ArrayList<String>> questionMap = makeRandomQuestionOptionsMap();
//                missgin questions issue is due to adding same question again to hashmap where must be unique keys
                HashMap<String, ArrayList<String>> currentQuestions = fullQuiz.getQuestionsAndOptions();
                HashMap<String, ArrayList<String>> newOptions = makeRandomQuestionOptionsMap();
//                System.out.println(newOptions);
                currentQuestions.put(newOptions.keySet().stream().findFirst().get(), newOptions.values().stream().findFirst().get());
            }
            for (int i = 0; i<fullQuiz.getQuestionsAndOptions().keySet().size(); i++){
//                adding optional in separate loop to account for repeat question changing the number of total questions (so the quiz won't be rejected for a null answer)
                ArrayList<Optional<String>> answerOptionals = fullQuiz.getAnswers();
                answerOptionals.add(Optional.empty());
                fullQuiz.setAnswers(answerOptionals);
            }
        }
        return fullQuiz;
    }

//    public void submitQuiz(Quiz quiz){
//        boolean success = quizDataAccessService.submitQuiz(quiz);
//        if (success){
//            evaluateQuiz(quiz.getId());
//        }
//    }

//    have an int return type to validate?
    public void submitQuiz(Quiz quiz){
        Optional<Integer> userId = quiz.getUserId();
        ArrayList<String> answersGiven = new ArrayList<>();
        for (Optional<String> ans: quiz.getAnswers()){
            if (ans.isPresent()){
                answersGiven.add(ans.get());
            } else if(ans.isEmpty()){
                throw new ResourceNotFound("Error: please select an answer");
            }
//            else{
//                return;
//
//            }
        }
        if (userId.isPresent()){
            playlistService.makePlaylist( answersGiven, userId.get());
        } else if (userId.isEmpty()){
            throw new ResourceNotFound("Error: please sign in");
        }
//        else {
//            return;
//        }
    }
//wip - needs mood as well as question and options
//    so outer hashmap key = question, which has a hashmap of options and moods
    public void addQuestion(HashMap<String, HashMap<String, String>> qAndOptions){
        String question = qAndOptions.keySet().stream().findFirst().get();
        HashMap<String, String> optionsAndMoods = qAndOptions.get(question);
//        add question
        quizDataAccessService.addQuestion(question);
//      get latest question id
        int newQId = quizDataAccessService.getNewQuestionId();
//        and add each option to the q with that id
        for(HashMap.Entry<String, String> kvSet : optionsAndMoods.entrySet()){
            quizDataAccessService.addOption(newQId, kvSet.getKey(), kvSet.getValue());

        }
    }

//    at some point need the user id when submitting
//    public Quiz makeQuiz(int id){
////        maybe more logic to have this just show the questions, should be fine for now
//        ArrayList<String> question = quizDataAccessService.getQuestion();
//    }



//    this needs lots of change without the quiz object, can be triggered when quiz gets submitted still
//    end of day just need to get the answers out of whatever form the quiz gets submitted
//    public void evaluateQuiz(int id){
////        trigger this once a quiz is submitted, have it get data from responses?
//        Quiz quiz = getQuizById(id);
//        ArrayList<Optional<String>> answers = new ArrayList<>();
//        HashMap<String, Optional<String>> qAndAMap = quiz.getQuestionsAndAnswers();
//        for (String key: qAndAMap.keySet()){
//            answers.add(qAndAMap.get(key));
//        }
////        some logic to get most common answer/distribution or filter answers etc
////        get the non-null values from optionals
//        ArrayList<String> nonNulls = new ArrayList<>(answers.stream().filter(Optional::isPresent).map(Optional::get)
//                .collect(Collectors.toList()));
////        call the playlist method that takes this and turns into playlist
////        check nonnulls has some values first else some error
//        if (nonNulls.size()!=0){
//            playlistService.makePlaylist(nonNulls, quiz.getUserId());
//        }
////        return nonNulls;
//    }



}
