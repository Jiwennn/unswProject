package au.espressolearning.javaapp;

public class Quiz {
    private String quizID;
    private String quizImage;
    private String topicName;
    private String quizQn;
    private Boolean answer;

    public Quiz(String topicName) {
        this.quizID = quizID;
        this.quizImage = quizImage;
        this.quizQn = quizQn;
        this.answer = answer;

    }

    public Quiz(String quizID, String quizImage, String quizQn, Boolean answer) {
        this.quizID = quizID;
        this.quizImage = quizImage;
        this.quizQn = quizQn;
        this.answer = answer;
    }
    public Quiz(String quizID, String quizImage, String quizQn, Boolean answer,String topicName) {
        this.quizID = quizID;
        this.quizImage = quizImage;
        this.quizQn = quizQn;
        this.topicName = topicName;
        this.answer = answer;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getQuizImage() {
        return quizImage;
    }

    public void setQuizImage(String quizImage) {
        this.quizImage = quizImage;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getQuizQn() {
        return quizQn;
    }

    public void setQuizQn(String quizQn) {
        this.quizQn = quizQn;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }
}
