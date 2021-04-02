package dk.nikolaj.fitnessappexam.adapter;
/**
 * @author Nikolaj
 */
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.model.FoodCategories;
import dk.nikolaj.fitnessappexam.storage.FoodCategories_storage;
import dk.nikolaj.fitnessappexam.storage.Recipes_storage;
import dk.nikolaj.fitnessappexam.ui.recipes.FoodCategoriesViewHolder;
import dk.nikolaj.fitnessappexam.ui.recipes.RecipesFragment;

public class FoodCategoriesAdapter extends RecyclerView.Adapter<FoodCategoriesViewHolder> {

    private static final String TAG = "FoodCategoriesAdapter";
    private final ArrayList<FoodCategories> list;
    private final Recipes_storage recipes_storage = new Recipes_storage();
    private final Context mCtx;

    public static final String foodCategoryKey = "CATEGORY_KEY";

    public FoodCategoriesAdapter(Context context) {
        mCtx = context;
        this.list = FoodCategories_storage.getList();
    }

    @NonNull
    @Override
    public FoodCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout c1 = null;
        c1 = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid_food_categories, parent, false);

        return new FoodCategoriesViewHolder(c1);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCategoriesViewHolder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipes_storage.setCategory(list.get(position).getHeadLine());

                Fragment fragment = new RecipesFragment();
                Bundle data = new Bundle();
                data.putString(foodCategoryKey, list.get(position).getHeadLine());
                fragment.setArguments(data);

                FragmentManager fm = ((AppCompatActivity) mCtx).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return FoodCategories_storage.getList().size();
    }
}
