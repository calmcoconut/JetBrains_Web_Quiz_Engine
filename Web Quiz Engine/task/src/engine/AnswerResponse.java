package engine;

public class AnswerResponse {
    private Boolean success;
    private String feedback;

    public AnswerResponse(Boolean success, String feedback) {
       this.success = success;
       this.feedback = feedback;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
