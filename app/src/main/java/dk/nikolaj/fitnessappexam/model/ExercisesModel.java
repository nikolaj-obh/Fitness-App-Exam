package dk.nikolaj.fitnessappexam.model;
/**
 * @author Anders & Osvald
 */
public class ExercisesModel {

    //Fields
    private String headLine;
    private String description;
    private String category;
    private String img;
    private String videoLink;
    private String reps, sets;

    public ExercisesModel() {
    }

    //Constructor
    public ExercisesModel(String headLine, String description, String category, String img, String videoLink, String sets, String reps) {
        this.headLine = headLine;
        this.description = description;
        this.category = category;
        this.img = img;
        this.videoLink = videoLink;
        this.sets = sets;
        this.reps = reps;
    }

    //Getters

    public String getHeadLine() {
        return headLine;
    }

    public String getDescription() {
        return description;

    }

    public String getCategory() {
        return category;
    }

    public String getImg() {
        return img;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public String getReps() {
        return reps;
    }

    public String getSets() {
        return sets;
    }
}
