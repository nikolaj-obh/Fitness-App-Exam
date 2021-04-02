package dk.nikolaj.fitnessappexam.model;
/**
 * @author Nikolaj
 */
public class Recipe {

    private String headLine;
    private String ingredients;
    private String description;
    private String category;
    private String imageLink;

    public Recipe() {
    }

    public Recipe(String headLine, String ingredients, String description, String category, String imageLink) {
        this.headLine = headLine;
        this.ingredients = ingredients;
        this.description = description;
        this.category = category;
        this.imageLink = imageLink;
    }

    public String getHeadLine() {
        return headLine;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImageLink() {
        return imageLink;
    }
}
