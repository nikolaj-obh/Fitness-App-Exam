package dk.nikolaj.fitnessappexam.ui.recipes;
/**
 * @author Nikolaj
 */
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.storage.FoodCategories_storage;

public class FoodCategoriesViewHolder extends RecyclerView.ViewHolder {

    private final TextView foodCategories_textView;
    private final ImageView foodCategories_imageView;

    public FoodCategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        ConstraintLayout constraintLayout = (ConstraintLayout) itemView;
        foodCategories_imageView = constraintLayout.findViewById(R.id.custom_grid_food_categories_image);
        foodCategories_textView = constraintLayout.findViewById(R.id.custom_grid_food_categories_textView);
    }

    public void setData(int rowLine) {
        FoodCategories_storage fcs = new FoodCategories_storage();
        fcs.downloadImage(FoodCategories_storage.list.get(rowLine).getHeadLine(),foodCategories_imageView);
        foodCategories_textView.setText(FoodCategories_storage.list.get(rowLine).getHeadLine());

    }
}
