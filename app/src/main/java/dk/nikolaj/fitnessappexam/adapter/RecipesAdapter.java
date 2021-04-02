package dk.nikolaj.fitnessappexam.adapter;
/**
 * @author Anders & Nikolaj
 */
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.model.Recipe;
import dk.nikolaj.fitnessappexam.storage.Recipes_storage;
import dk.nikolaj.fitnessappexam.ui.recipes.DetailedRecipeActivity;
import dk.nikolaj.fitnessappexam.ui.recipes.RecipesViewHolder;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesViewHolder> {

    private final ArrayList<Recipe> list;

    public static final String headlineKey = "HEADLINE_KEY";
    public static final String ingredientsKey = "INGREDIENTS_KEY";
    public static final String imageLinkKey = "IMAGELINK_KEY";
    public static final String descriptionKey = "DESCRIPTION_KEY";

    public static final String recipeKey = "RECIPE_KEY";

    private final Context mCtx;

    public RecipesAdapter(Context context) {
        this.mCtx = context;
        this.list = Recipes_storage.getDisplayedList();
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout c1 = null;
        c1 = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid_recipes, parent, false);

        return new RecipesViewHolder(c1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailedRecipeActivity.class);
                intent.putExtra(headlineKey, Recipes_storage.getDisplayedList().get(position).getHeadLine());
                intent.putExtra(ingredientsKey, Recipes_storage.getDisplayedList().get(position).getIngredients());
                intent.putExtra(imageLinkKey, Recipes_storage.getDisplayedList().get(position).getImageLink());
                intent.putExtra(descriptionKey, Recipes_storage.getDisplayedList().get(position).getDescription());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Recipes_storage.getDisplayedList().size();
    }
}