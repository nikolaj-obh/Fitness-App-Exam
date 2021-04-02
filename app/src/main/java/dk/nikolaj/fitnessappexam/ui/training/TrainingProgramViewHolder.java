package dk.nikolaj.fitnessappexam.ui.training;
/**
 * @author Anders & Osvald
 */
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.storage.Training_storage;

public class TrainingProgramViewHolder extends RecyclerView.ViewHolder {

    private TextView exercise_number;
    private TextView excercise_name;
    private TextView excercise_reps_sets;


    public TrainingProgramViewHolder(@NonNull View itemView) {
        super(itemView);
        ConstraintLayout constraintLayout = (ConstraintLayout) itemView;

        exercise_number = constraintLayout.findViewById(R.id.exercise_number);
        excercise_name = constraintLayout.findViewById(R.id.exercise_name);
        excercise_reps_sets = constraintLayout.findViewById(R.id.exercise_reps);
    }

    public void setData(int rowLine) {
        System.out.println("THIS IS AN ERROR MESSAGE " + rowLine + "    " + Training_storage.getTrainingSchemaListDisplayed().size() );
        exercise_number.setText(String.valueOf(rowLine+1));
        excercise_reps_sets.setText(Training_storage.getTrainingSchemaListDisplayed().get(rowLine).getSets() + "/" + Training_storage.getTrainingSchemaListDisplayed().get(rowLine).getReps());
        excercise_name.setText(Training_storage.getTrainingSchemaListDisplayed().get(rowLine).getHeadLine());
    }
}
