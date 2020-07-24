package engine.repository;

import engine.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizRepository {
    private static List<Quiz> quizzes = new ArrayList<Quiz>();
    public static int CURRENT_ID = 1;

    public QuizRepository() {
    }

    public void addQuiz(String title, String text, List<String> options, int answer) {
        quizzes.add(new Quiz(title, text, options, answer));
        CURRENT_ID++;
    }
    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
        CURRENT_ID++;
    }

    public Quiz getQuizById(int id) {
        return quizzes.stream().filter(q -> q.getId() == id).findFirst().get();
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }
}
