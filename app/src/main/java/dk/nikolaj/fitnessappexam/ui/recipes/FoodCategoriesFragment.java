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

public class FoodCategoriesFragment extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FoodCategoriesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_foodcategories, container, false);

        recyclerView = v.findViewById(R.id.foodcategories_main_recycler);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new FoodCategoriesAdapter(getContext());
        recyclerView.setAdapter(adapter);

        return v;
    }

    public void onResume() {
        super.onResume();

        // Sets toolbar title to following string
        getActivity().setTitle("Recipes");

    }
}
