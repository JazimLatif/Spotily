package com.spotily.app.quiz;

import com.spotily.app.playlist.Playlist;
import com.spotily.app.playlist.PlaylistDataAccessService;
import com.spotily.app.playlist.PlaylistService;
import com.spotily.app.playlist.filterplaylist.FilterPlaylist;
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
        return quizDataAccessService.getRandomQuestion().orElseThrow(()-> new ResourceNotFound("Could not find a question"));
    }

    public String getThemedQ(int theme){
        return quizDataAccessService.getThemedQuestion(theme).orElseThrow(()-> new ResourceNotFound("No questions found with that theme"));
    }

    public ArrayList<String> getQOptions(String question){
        ArrayList<String> options = quizDataAccessService.getQuestionOptions(question);
        if (options.size()==0){
            throw new ResourceNotFound("No options for that questions, an admin can add them");
        }
        return options;
    }

    public HashMap<String, ArrayList<String>> makeRandomQuestionOptionsMap(){
        String randQ = getRandomQ();
        ArrayList<String> matchingOptions = getQOptions(randQ);
        HashMap options_map = new HashMap<String, ArrayList<String>>();
        options_map.put(randQ, matchingOptions);
        return options_map;
    }

    public HashMap<String, ArrayList<String>> makeThemedQuestionOptionsMap(int theme){
        String randQ = getThemedQ(theme);
        ArrayList<String> matchingOptions = getQOptions(randQ);
        HashMap options_map = new HashMap<String, ArrayList<String>>();
        options_map.put(randQ, matchingOptions);
        return options_map;
    }

//    could have number of questions be a argument for the function? if want to vary
    public Quiz makeEmptyQuiz(){
        Quiz fullQuiz = new Quiz(Optional.empty(), new HashMap<>(), new ArrayList<>());

        if (fullQuiz == null){
            throw new ResourceNotFound("Error: quiz couldn't be created");

        } else {
            for (int i = 0; i < 5; i++) {
                HashMap<String, ArrayList<String>> currentQuestions = fullQuiz.getQuestionsAndOptions();
                HashMap<String, ArrayList<String>> newOptions = makeRandomQuestionOptionsMap();
                currentQuestions.put(newOptions.keySet().stream().findFirst().get(), newOptions.values().stream().findFirst().get());
            }
            for (int i = 0; i<fullQuiz.getQuestionsAndOptions().keySet().size(); i++){
//                adding optional in separate loop to account for repeat question changing the number of total questions
//                (so the number of answers will match number of questions after duplicates from random choice are gone)
                ArrayList<Optional<String>> answerOptionals = fullQuiz.getAnswers();
                answerOptionals.add(Optional.empty());
                fullQuiz.setAnswers(answerOptionals);
            }
        }
        return fullQuiz;
    }

    public Quiz makeThemedQuiz(int theme){
        Quiz fullQuiz = new Quiz(Optional.empty(), new HashMap<>(), new ArrayList<>());
        if (fullQuiz == null){
            throw new ResourceNotFound("Error: quiz couldn't be created");
        } else {
            for (int i = 0; i < 5; i++) {
                HashMap<String, ArrayList<String>> currentQuestions = fullQuiz.getQuestionsAndOptions();
                HashMap<String, ArrayList<String>> newOptions = makeThemedQuestionOptionsMap(theme);
                currentQuestions.put(newOptions.keySet().stream().findFirst().get(), newOptions.values().stream().findFirst().get());
            }
            for (int i = 0; i<fullQuiz.getQuestionsAndOptions().keySet().size(); i++){
                ArrayList<Optional<String>> answerOptionals = fullQuiz.getAnswers();
                answerOptionals.add(Optional.empty());
                fullQuiz.setAnswers(answerOptionals);
            }
        }
        return fullQuiz;
    }

    public void submitQuiz(Quiz quiz){
        Optional<Integer> userId = quiz.getUserId();
        ArrayList<String> answersGiven = new ArrayList<>();
        for (Optional<String> ans: quiz.getAnswers()){
            if (ans.isPresent()){
                answersGiven.add(ans.get());
            } else if(ans.isEmpty()){
                throw new ResourceNotFound("Error: please select an answer");
            }
        }
        if (userId.isPresent()){
            playlistService.makePlaylist( answersGiven, userId.get());
        } else if (userId.isEmpty()){
            throw new ResourceNotFound("Error: please sign in");
        }
    }
    public void submitThemedQuiz(Quiz quiz, int theme){
        Optional<Integer> userId = quiz.getUserId();
        ArrayList<String> answersGiven = new ArrayList<>();
        for (Optional<String> ans: quiz.getAnswers()){
            if (ans.isPresent()){
                answersGiven.add(ans.get());
            } else if(ans.isEmpty()){
                throw new ResourceNotFound("Error: please select an answer");
            }
        }
        if (userId.isPresent()){
            playlistService.makeThemedPlaylist( answersGiven, userId.get(), theme);
        } else if (userId.isEmpty()){
            throw new ResourceNotFound("Error: please sign in");
        }
    }

    public void addQuestion(Question questionObj, int id){
        Optional<ArrayList<Integer>> adminIds = quizDataAccessService.getAdmin();
        if (adminIds.isEmpty()){
            throw new ResourceNotFound("No admin users available, unable to submit");
        }
        else if (!adminIds.get().contains(id)){
            throw new UnsupportedOperationException("Only Admins are allowed to add questions");
        }
        String question = questionObj.getText();
        Optional<Integer> theme = questionObj.getTheme();
        HashMap<String, String> optionsAndMoods = questionObj.getOptionsAndMoods();
//        add question
            if (theme.isPresent()){
                quizDataAccessService.addThemedQuestion(question, theme.get());
            }else {
                quizDataAccessService.addQuestion(question);
            }
//      get latest question id
        Optional<Integer> newQId = quizDataAccessService.getNewQuestionId();
            if (newQId.isEmpty()){
                throw new ResourceNotFound("Error, question not found");
            }
//        and add each option to the q with that id
        for(HashMap.Entry<String, String> kvSet : optionsAndMoods.entrySet()){
            quizDataAccessService.addOption(newQId.get(), kvSet.getKey(), kvSet.getValue());
        }
    }

    public void updateQuestion(Question question, int userId, int questionId){
//        check user is admin
        Optional<ArrayList<Integer>> adminIds = quizDataAccessService.getAdmin();
        if (adminIds.isEmpty()){
            throw new ResourceNotFound("No admin users available, unable to submit");
        }
        else if (!adminIds.get().contains(userId)){
            throw new UnsupportedOperationException("Only Admins are allowed to update questions");
        }
//      check question exists
        Optional<ArrayList<Integer>> questionIds = quizDataAccessService.getAllQuestionIds();
        if (questionIds.isEmpty()){
            throw new ResourceNotFound("No questions found");
        }
        else if (!questionIds.get().contains(questionId)){
            throw new ResourceNotFound("Question with that ID not found");
        }

//        update the question
        quizDataAccessService.updateQuestion(question.getText(), questionId);

//        update the options - clear existing options where the question id matches
        quizDataAccessService.deleteOptionsByQuestionId(questionId);

//        update the options - add each option to matching question id
        HashMap<String, String> optionsAndMoods = question.getOptionsAndMoods();
        for(HashMap.Entry<String, String> kvSet : optionsAndMoods.entrySet()){
            quizDataAccessService.addOption(questionId, kvSet.getKey(), kvSet.getValue());
        }
    }

    public List<FilterPlaylist> returnLatestPlaylist(){
        return playlistService.selectPlaylistById(playlistService.getMaxPlaylistIdTest());
    }

}
