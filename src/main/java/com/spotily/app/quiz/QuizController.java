package com.spotily.app.quiz;

import com.spotily.app.playlist.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

//    @PostMapping
//    public void submitQuiz(@RequestBody Quiz quiz){
////        do we need to do more here to create a quiz object?
//        quizService.submitQuiz(quiz);
//    }

//    @GetMapping("{id}")
//    public Quiz getQuiz(@PathVariable("id") int id){
//        return quizService.getQuizQuestions(id);
//    }

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

//    gets question - works, but shows the oddness of current quiz obj, may rejig
    @GetMapping("/fullquestiontest")
    public HashMap getFullQuestionsRandomTest(){
        return quizService.makeRandomQuestionOptionsMap();
    }

//    tested in thunder client - working but with an issue, hardcoded a number of questions
//    which it is sometimes over and undershooting
//    could cause issues when checking for null answers - but could maybe just add the answer optionals in the same loop lol
    @GetMapping("/fullquiztest")
    public Quiz getFullQuizTest(){
        return quizService.makeEmptyQuiz();
    }

//tested in thunder client - working, creates new playlist with songs w correct tags
    @PostMapping("/submitTest")
    public void submitQuizTest(@RequestBody Quiz quiz){
        quizService.submitQuiz(quiz);
    }

//    tested in thunder client - successfully adds question and all options
//    now also checks if user is an admin and doesn't add if not
    @PostMapping("{id}/submit-question")
    public void submitQuestionTest(@PathVariable("id") int id, @RequestBody Question question){
        quizService.addQuestion(question, id);
    }
//    need to adjust so that

// tested in thunder client successfully
    @PostMapping("/submitthemed/{tag}")
    public void submitThemedQuizTest(@RequestBody Quiz quiz, @PathVariable("tag") int tag){ quizService.submitThemedQuiz(quiz, tag); }

//    tested in thuder client - working
    @GetMapping("/getthemed/{tag}")
    public Quiz getThemedQuizTest(@PathVariable("tag") int tag){ return quizService.makeThemedQuiz(tag); }



}
