package engine.controller;

import engine.model.AnswerResponse;
import engine.model.Quiz;
import engine.repository.QuizRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    QuizRepository quizRepository = new QuizRepository();

    public TaskController() {
    }

    @GetMapping(value = "/api/quizzes/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Quiz getQuiz(@PathVariable("id") int id) {
        return quizRepository.getQuizById(id);
    }

    @GetMapping(value = "/api/quizzes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.getQuizzes() != null ?
                quizRepository.getQuizzes() : new ArrayList<>();
        return quizRepository.getQuizzes();
    }

    @PostMapping(value = "/api/quizzes",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = "application/json")
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        quizRepository.addQuiz(quiz);
        return quiz;
    }

    @PostMapping(value = "/api/quizzes/{id}/solve",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = "application/x-www-form-urlencoded")
    public AnswerResponse answerQuiz(@PathVariable("id") int id, @RequestParam("answer") int answer) {
        return quizRepository.getQuizById(id).answerResponse(answer);
    }
}
