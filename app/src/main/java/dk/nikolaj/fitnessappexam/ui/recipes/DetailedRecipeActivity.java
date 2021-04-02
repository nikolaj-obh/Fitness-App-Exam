package dk.nikolaj.fitnessappexam.ui.recipes;
/**
 * @author Nikolaj
 */
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.adapter.RecipesAdapter;
import dk.nikolaj.fitnessappexam.storage.Recipes_storage;

public class DetailedRecipeActivity extends AppCompatActivity {

    private TextView headlineView, ingredientsView, descriptionView;
    private String headline, ingredients, imageLink, description;
    private ImageView imageView;
    private Boolean hasImage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_recipe);

        headlineView = findViewById(R.id.headline_detailed_recipe);
        ingredientsView = findViewById(R.id.ingredients_detailed_recipe);
        imageView = findViewById(R.id.recipe_imageView);
        descriptionView = findViewById(R.id.description_detailed_recipe);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            headline = intent.getExtras().getString(RecipesAdapter.headlineKey);
            ingredients = intent.getExtras().getString(RecipesAdapter.ingredientsKey);
            imageLink = intent.getExtras().getString(RecipesAdapter.imageLinkKey);
            description = intent.getExtras().getString(RecipesAdapter.descriptionKey);

            headlineView.setText(headline);
            ingredientsView.setText(ingredients);
            descriptionView.setText(description);

            if (imageLink != null && imageLink.length() > 0) {
                //If there's an image in the log, then download it, change some button txt and show a new button
                hasImage = true;
                Recipes_storage.downloadImage(imageLink, imageView);
            }
        }
    }
}