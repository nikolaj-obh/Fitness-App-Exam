package dk.nikolaj.fitnessappexam.ui.recipes;
/**
 * @author Nikolaj, Osvald & Anders
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.storage.Recipes_storage;

public class RecipesViewHolder extends RecyclerView.ViewHolder {

    private final TextView recipes_tv_title, recipes_tv_desc;
    private final ImageView recipes_imageView;

    public RecipesViewHolder(@NonNull View itemView) {
        super(itemView);
        ConstraintLayout constraintLayout = (ConstraintLayout) itemView;
        recipes_imageView = constraintLayout.findViewById(R.id.custom_grid_recipeImg);
        recipes_tv_desc = constraintLayout.findViewById(R.id.custom_grid_recipeDescription);
        recipes_tv_title = constraintLayout.findViewById(R.id.custom_grid_recipeTitle);
    }

    public void setData(int rowLine) {
        Recipes_storage rs = new Recipes_storage();
        rs.downloadImage(Recipes_storage.displayedList.get(rowLine).getImageLink(),recipes_imageView);
        recipes_tv_desc.setText(Recipes_storage.displayedList.get(rowLine).getDescription());
        recipes_tv_title.setText(Recipes_storage.displayedList.get(rowLine).getHeadLine());
    }
}