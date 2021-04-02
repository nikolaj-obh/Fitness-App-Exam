package dk.nikolaj.fitnessappexam.model;
/**
 * @author Anders & Osvald
 */
public class CategoriesModel {

    //Fields
    private String headLine;
    private String categoryImg;

    public CategoriesModel() {
    }

    //Constructor
    public CategoriesModel(String headLine, String categoryImg) {
        this.headLine = headLine;
        this.categoryImg = categoryImg;
    }

    //Getters
    public String getHeadLine() {
        return headLine;
    }

    public String getCategoryImg() {
        return categoryImg;
    }
}
