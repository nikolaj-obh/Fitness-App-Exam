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

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.adapter.CategoriesAdapter;
import dk.nikolaj.fitnessappexam.adapter.ExerciseAdapter;
import dk.nikolaj.fitnessappexam.storage.Exercises_storage;

public class ExercisesFragment extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ExerciseAdapter adapter;

    private String chosenCategory;

    private final Exercises_storage exercise_storage = new Exercises_storage();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_exercises, container, false);

        recyclerView = v.findViewById(R.id.exercises_main_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ExerciseAdapter();
        recyclerView.setAdapter(adapter);

        Bundle data = getArguments();
        if (data != null) {
            chosenCategory = data.getString(CategoriesAdapter.categoryKey);
        }

        return v;
    }

    public void onResume(){
        super.onResume();

        // Sets toolbar title to following string
        getActivity().setTitle("Exercises - " + chosenCategory);
        exercise_storage.sortList();
    }

}