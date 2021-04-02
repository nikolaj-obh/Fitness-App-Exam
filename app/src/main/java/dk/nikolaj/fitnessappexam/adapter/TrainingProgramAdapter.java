package dk.nikolaj.fitnessappexam.adapter;
/**
 * @author Anders & Osvald
 */
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.storage.Training_storage;
import dk.nikolaj.fitnessappexam.ui.exercises.DetailedExercisesActivity;
import dk.nikolaj.fitnessappexam.ui.training.TrainingProgramViewHolder;

import static dk.nikolaj.fitnessappexam.adapter.ExerciseAdapter.descriptionKey;
import static dk.nikolaj.fitnessappexam.adapter.ExerciseAdapter.headlineKey;
import static dk.nikolaj.fitnessappexam.adapter.ExerciseAdapter.videolinkKey;

public class TrainingProgramAdapter extends RecyclerView.Adapter<TrainingProgramViewHolder> {

    private Context context;
    public static final String repsKey = "REPS_KEY";
    public static final String setsKey = "SETS_KEY";



    public TrainingProgramAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TrainingProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout c1 = null;
        c1 = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_program, parent, false);

        return new TrainingProgramViewHolder(c1);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingProgramViewHolder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailedExercisesActivity.class);
                intent.putExtra(headlineKey, Training_storage.getTrainingSchemaListDisplayed().get(position).getHeadLine());
                intent.putExtra(descriptionKey, Training_storage.getTrainingSchemaListDisplayed().get(position).getDescription());
                intent.putExtra(videolinkKey, Training_storage.getTrainingSchemaListDisplayed().get(position).getVideoLink());
                intent.putExtra(repsKey, Training_storage.getTrainingSchemaListDisplayed().get(position).getReps());
                intent.putExtra(setsKey, Training_storage.getTrainingSchemaListDisplayed().get(position).getSets());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Training_storage.getTrainingSchemaListDisplayed().size();
    }

}
