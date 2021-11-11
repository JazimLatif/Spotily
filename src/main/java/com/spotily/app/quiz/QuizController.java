package com.spotily.app.quiz;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private QuizService quizService;

    @PostMapping
    public void submitQuiz(@RequestBody Quiz quiz){
//        do we need to do more here to create a quiz object?
        quizService.submitQuiz(quiz);
    }

    @GetMapping("{id}")
    public Quiz getQuiz(@PathVariable("id") int id){
        return quizService.getQuizQuestions(id);
    }

}
