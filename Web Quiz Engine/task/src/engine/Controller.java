package engine;

import engine.quiz.Quiz;
import engine.quiz.QuizAnswer;
import engine.quiz.QuizResult;
import engine.quiz.QuizService;
import engine.user.User;
import engine.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated()
@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class Controller {
    @Autowired
    QuizService quizService;
    @Autowired
    UserService userService;

    @GetMapping("/quizzes")
    public List<Quiz> getQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuiz(@PathVariable @Min(1) int id) {
        try {
            return quizService.getQuiz(id);
        } catch (IndexOutOfBoundsException ignored) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Required quiz not available");
        }
    }

    @PostMapping("/quizzes")
    public Quiz addQuiz(@RequestBody @Valid @NotNull Quiz quiz, Authentication authentication) {
        quizService.addQuiz(quiz, authentication);
        return quiz;
    }

    @PostMapping("/quizzes/{id}/solve")
    public QuizResult submitQuiz(@PathVariable @Min(1) int id,
                                 @RequestBody @NotNull QuizAnswer answer,
                                 Authentication authentication) {
        return quizService.evaluateQuiz(id, answer, authentication);
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid User user) {
        userService.registerUser(user);
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity deleteQuiz(@PathVariable @Min(value = 1, message = "ID must 1 or greater") int id,
                                     Authentication authentication) {
        return quizService.deleteQuiz(id, authentication);
    }

    @PutMapping("/quizzes/{id}")
    public ResponseEntity deleteQuiz(@PathVariable @Min(value = 1, message = "ID must 1 or greater") int id,
                                     @RequestBody @Valid @NotNull Quiz quiz, Authentication authentication) {
        return quizService.updateQuiz(id, quiz, authentication);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}