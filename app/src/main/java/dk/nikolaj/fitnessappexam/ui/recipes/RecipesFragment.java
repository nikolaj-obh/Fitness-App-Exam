package dk.nikolaj.fitnessappexam.ui.recipes;
/**
 * @author Nikolaj
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.adapter.FoodCategoriesAdapter;
import dk.nikolaj.fitnessappexam.adapter.RecipesAdapter;
import dk.nikolaj.fitnessappexam.storage.Recipes_storage;

public class RecipesFragment extends Fragment {

    private final Recipes_storage recipes_storage = new Recipes_storage();
    private View v;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecipesAdapter adapter;

    private String chosenFoodCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_recipes, container, false);

        recipes_storage.sortList();

        recyclerView = v.findViewById(R.id.recipes_main_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new RecipesAdapter(getContext());
        recyclerView.setAdapter(adapter);

        Bundle data = getArguments();
        if (data != null) {
            chosenFoodCategory = data.getString(FoodCategoriesAdapter.foodCategoryKey);
        }
        return v;
    }

    public void onResume() {
        super.onResume();

        // Sets toolbar title to following string
        getActivity().setTitle("Recipes - " + chosenFoodCategory);
    }
}