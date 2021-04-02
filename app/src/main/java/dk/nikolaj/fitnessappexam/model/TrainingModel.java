package dk.nikolaj.fitnessappexam.model;
/**
 * @author Anders & Osvald
 */
public class TrainingModel {

    private String Day;
    private String category;
    private String reps, sets;
    private String headLine;
    private String videoLink;
    private String description;


    public TrainingModel(String day, String category) {
        Day = day;
        this.category = category;
    }

    public TrainingModel(String sets, String reps, String headLine) {
        this.sets = sets;
        this.reps = reps;
        this.headLine = headLine;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public String getDescription() {
        return description;
    }

    public TrainingModel() {
    }

    public String getDay() {
        return Day;
    }

    public String getCategory() {
        return category;
    }

    public String getReps() {
        return reps;
    }

    public String getSets() {
        return sets;
    }

    public String getHeadLine() {
        return headLine;
    }

}
