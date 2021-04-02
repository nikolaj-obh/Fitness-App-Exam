package dk.nikolaj.fitnessappexam.model;
/**
 * @author Nikolaj
 */
public class FoodCategories {

    private String headLine, categoryImg;

    public FoodCategories() {
    }

    public FoodCategories(String headLine, String categoryImg) {
        this.headLine = headLine;
        this.categoryImg = categoryImg;
    }

    public String getHeadLine() {
        return headLine;
    }

    public String getCategoryImg() {
        return categoryImg;
    }
}
