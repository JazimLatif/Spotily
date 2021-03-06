package com.spotily.app.quiz;

import com.spotily.app.playlist.PlaylistService;
import com.spotily.app.playlist.filterplaylist.FilterPlaylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


//    get text of a random q - successfully tested
    @GetMapping("/randomq")
    public String getRandomQuestionTest(){
        return quizService.getRandomQ();
    }

//    get all the options for a question given the question text - successfully tested
    @GetMapping
    public List getquestionOptionsTest(){
        String testQ = "How are you feeling?";
        return quizService.getQOptions(testQ);
    }

//    gets question - works, but shows the oddness of current quiz obj, now fixed
    @GetMapping("/fullquestiontest")
    public HashMap getFullQuestionsRandomTest(){
        return quizService.makeRandomQuestionOptionsMap();
    }


//tested in thunder client - working, creates new playlist with songs w correct tags
    @PostMapping("/submitTest")
    public void submitQuizTest(@RequestBody Quiz quiz){
        quizService.submitQuiz(quiz);
    }

//    tested in thunder client - successfully adds question and all options
//    now also checks if user is an admin and doesn't add if not
    @PostMapping("{id}/submit-question")
    public void submitQuestionTest(@PathVariable("id") int userId, @RequestBody Question question){
        quizService.addQuestion(question, userId);
    }

//    tested in thunder client - working
    @PutMapping("{userid}/update-question/{questionid}")
    public void updateQuestionTest(@PathVariable("userid") int userId, @PathVariable("questionid") int qId, @RequestBody Question question){
        quizService.updateQuestion(question, userId, qId);
    }


// tested in thunder client successfully
    @PostMapping("/submit-themed/{tag}")
    public void submitThemedQuiz(@RequestBody Quiz quiz, @PathVariable("tag") int tag){ quizService.submitThemedQuiz(quiz, tag); }

//    tested in thunder client - working
    @GetMapping("/get-themed/{tag}")
    public Quiz getThemedQuiz(@PathVariable("tag") int tag){ return quizService.makeThemedQuiz(tag); }

// tested in thunderclient - working
    @PostMapping("/submit-themed-return/{tag}")
    public List<FilterPlaylist> submitThemedQuizReturnPlaylist(@RequestBody Quiz quiz, @PathVariable("tag") int tag){
        quizService.submitThemedQuiz(quiz, tag);
        return quizService.returnLatestPlaylist();
    }

//    tested in thunderclient - working
    @PostMapping("/submit-return")
    public List<FilterPlaylist> submitQuizReturnPlaylist(@RequestBody Quiz quiz){
        quizService.submitQuiz(quiz);
        return quizService.returnLatestPlaylist();
    }

    //    tested in thunder client - working but with an issue - now fixed
    @GetMapping("/getquiz")
    public Quiz getFullQuiz(){
        return quizService.makeEmptyQuiz();
    }

//    tested in thunder client - working
    @DeleteMapping("/delete/{userId}/{questionId}")
    public void deleteQuestionById(@PathVariable("userId") int userId, @PathVariable("questionId") int questionId){
        quizService.deleteQuestion(userId, questionId);
    }

}
