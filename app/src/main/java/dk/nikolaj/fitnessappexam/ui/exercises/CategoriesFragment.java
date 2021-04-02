package dk.nikolaj.fitnessappexam.ui.exercises;
/**
 * @author Anders & Osvald
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dk.nikolaj.fitnessappexam.adapter.CategoriesAdapter;
import dk.nikolaj.fitnessappexam.R;

public class CategoriesFragment extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CategoriesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView = v.findViewById(R.id.categories_main_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CategoriesAdapter(getContext());
        recyclerView.setAdapter(adapter);

        return v;
    }

    public void onResume() {
        super.onResume();

        // Sets toolbar title to following string
        getActivity().setTitle("Exercises");

    }
}
