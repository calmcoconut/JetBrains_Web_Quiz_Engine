package engine;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    private static List<Quiz> quizzes = new ArrayList<>();


    static {
        quizzes.add(new Quiz("The Java Logo",
                "What is depicted on the Java logo?",
                List.of("Robot", "Tea leaf", "cup of coffee", "Bug"),
                2));
    }

    public TaskController() {
    }

    @GetMapping(value = "/api/quiz", produces = MediaType.APPLICATION_JSON_VALUE)
    public Quiz getQuiz() {
        return quizzes.get(0);
    }

    @PostMapping(value = "/api/quiz",produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/x-www-form-urlencoded")
    public AnswerResponse answerQuiz(@RequestParam("answer") int answer) {
        return quizzes.get(0).answerResponse(answer);
    }
}
