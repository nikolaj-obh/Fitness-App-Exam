package dk.nikolaj.fitnessappexam.adapter;
/**
 * @author Anders & Osvald
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
import dk.nikolaj.fitnessappexam.model.CategoriesModel;
import dk.nikolaj.fitnessappexam.storage.Categories_storage;
import dk.nikolaj.fitnessappexam.storage.Exercises_storage;
import dk.nikolaj.fitnessappexam.ui.exercises.CategoriesViewHolder;
import dk.nikolaj.fitnessappexam.ui.exercises.ExercisesFragment;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private final ArrayList<CategoriesModel> list;

    private final Exercises_storage exercise_storage = new Exercises_storage();

    private final Context mCtx;
    private static final String TAG = "CategoriesAdapter";

    public static final String categoryKey = "CATEGORY_KEY";

    public CategoriesAdapter(Context context) {
        mCtx = context;
        this.list = Categories_storage.getList();
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout c1 = null;
        c1 = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);

        return new CategoriesViewHolder(c1);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoriesViewHolder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exercises_storage.setCategory(list.get(position).getHeadLine());

                Fragment fragment = new ExercisesFragment();
                Bundle data = new Bundle();
                data.putString(categoryKey, list.get(position).getHeadLine());
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
        return Categories_storage.getList().size();
    }

}
