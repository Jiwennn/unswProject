package au.espressolearning.javaapp;

public class Course {

    private String topicName;
    private int courseID;
    private String description;
    private String url;

    public Course(String topicName, int courseID, String description, String url) {
        this.topicName = topicName;
        this.courseID = courseID;
        this.description = description;
        this.url = url;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl(){return url;}
}
