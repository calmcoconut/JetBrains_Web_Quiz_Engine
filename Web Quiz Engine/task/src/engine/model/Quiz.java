package engine.model;

import engine.model.AnswerResponse;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int id;
    private String title;
    private String text;
    private List<String> options = new ArrayList<>();
    private int answerIndex;

    public Quiz(int id, String title, String text, List<String> options, int answerIndex) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answerIndex = answerIndex;
    }

    public AnswerResponse answerResponse(int answer) {
        if (answer == this.answerIndex) {
            return new AnswerResponse(true, "Congratulations, you're right!");
        }
        else {
            return new AnswerResponse(false, "Wrong answer! Please, try again.");
        }
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public List<String> getOptions() {
        return this.options;
    }

    public int getId() {
        return id;
    }
}
