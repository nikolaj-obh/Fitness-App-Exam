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
import dk.nikolaj.fitnessappexam.model.TrainingModel;
import dk.nikolaj.fitnessappexam.storage.Training_storage;
import dk.nikolaj.fitnessappexam.ui.training.TrainingProgramFragment;
import dk.nikolaj.fitnessappexam.ui.training.TrainingViewHolder;

import static dk.nikolaj.fitnessappexam.adapter.CategoriesAdapter.categoryKey;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingViewHolder> {

    private static final String TAG = "TrainingAdapter";
    private Context mCtx;

    private ArrayList<TrainingModel> list;

    public TrainingAdapter() {

    }

    public TrainingAdapter(Context context) {
        mCtx = context;
        this.list = Training_storage.getTrainingDayList();
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout c1 = null;
        c1 = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_training, parent, false);

        return new TrainingViewHolder(c1);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Training_storage.getTrainingDayList().get(position).getCategory().contains("Rest")){
                    return;
                }
                Training_storage.setCategory(Training_storage.getTrainingDayList().get(position).getCategory());

                Fragment fragment = new TrainingProgramFragment();
                Bundle data = new Bundle();
                data.putString(categoryKey, list.get(position).getCategory());
                fragment.setArguments(data);
                FragmentManager fm = ((AppCompatActivity)mCtx).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return Training_storage.getTrainingDayList().size();
    }
}
