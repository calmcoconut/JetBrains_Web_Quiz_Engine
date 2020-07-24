package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import engine.repository.QuizRepository;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int id;
    private String title;
    private String text;
    private List<String> options = new ArrayList<>();
    private int answerIndex;

    public Quiz(@JsonProperty("title") String title,
                @JsonProperty("text") String text,
                @JsonProperty("options") List<String> options,
                @JsonProperty("answer") int answerIndex) {
        this.id = QuizRepository.CURRENT_ID;
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

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }
}
