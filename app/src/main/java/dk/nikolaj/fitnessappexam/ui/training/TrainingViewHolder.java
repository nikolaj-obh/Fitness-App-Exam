package dk.nikolaj.fitnessappexam.ui.training;
/**
 * @author Anders & Osvald
 */
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.storage.Training_storage;

public class TrainingViewHolder extends RecyclerView.ViewHolder {

    private final TextView muscle_textView;
    private final TextView day_textView;
    private final ImageView training_imageView;

    public TrainingViewHolder(@NonNull View itemView) {
        super(itemView);
        ConstraintLayout constraintLayout = (ConstraintLayout) itemView;
        muscle_textView = constraintLayout.findViewById(R.id.muscleGroupTextView);
        day_textView = constraintLayout.findViewById(R.id.dayTextView);
        training_imageView = constraintLayout.findViewById(R.id.TrainingImageView);
    }


    public void setData(int rowLine) {
        training_imageView.setImageBitmap(null);
        muscle_textView.setText(Training_storage.getTrainingDayList().get(rowLine).getCategory());
        day_textView.setText(Training_storage.getTrainingDayList().get(rowLine).getDay());
    }
}