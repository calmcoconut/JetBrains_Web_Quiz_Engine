package engine.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    private final static QuizResult
            correctAns = new QuizResult(true, "Congratulations, you're right!"),
            wrongAns = new QuizResult(false, "Wrong answer! Please, try again.");

    QuizService() {
    }

    public List<Quiz> getAllQuizzes() {
        return (List<Quiz>) quizRepository.findAll();
    }

    public Quiz getQuiz(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        return quiz.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Requested quiz not found"));
    }

    public Quiz addQuiz(Quiz quiz, Authentication authentication) {
        quiz.setCreator(authentication.getName());
        return quizRepository.save(quiz);
    }

    public QuizResult evaluateQuiz(int id, QuizAnswer answer, Authentication authenticationh) {
        int[] originalAnswer = getQuiz(id).getAnswer();
        int[] providedAnswer = answer.getAnswer();

        if (originalAnswer.length != providedAnswer.length) return wrongAns;
        for (int i = 0; i < originalAnswer.length; i++) {
            if (originalAnswer[i] != providedAnswer[i]) return wrongAns;
        }
        
        return correctAns;
    }

    public ResponseEntity deleteQuiz(int id, Authentication authentication) {
        Quiz quiz = getQuiz(id);
        if (quiz.getCreator().equals(authentication.getName())) {
            quizRepository.delete(quiz);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
        }
    }

    public ResponseEntity updateQuiz(int id, Quiz quiz, Authentication authentication) {
        if (quizRepository.existsById(id)) {
            if (getQuiz(id).getCreator().equals(authentication.getName())) {
                quiz.setId(id);
                return new ResponseEntity(addQuiz(quiz, authentication), HttpStatus.OK);
            }
            throw new RuntimeException("Forbidden");
        }
        else {
            return new ResponseEntity(addQuiz(quiz, authentication), HttpStatus.CREATED);
        }
    }
}