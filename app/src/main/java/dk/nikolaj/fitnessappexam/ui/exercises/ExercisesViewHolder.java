package dk.nikolaj.fitnessappexam.ui.exercises;
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
import dk.nikolaj.fitnessappexam.storage.Exercises_storage;

public class ExercisesViewHolder extends RecyclerView.ViewHolder {

    private final TextView exercises_textView;
    private final ImageView exercises_imageView;

    public ExercisesViewHolder(@NonNull View itemView) {
        super(itemView);
        ConstraintLayout constraintLayout = (ConstraintLayout) itemView;
        exercises_imageView = constraintLayout.findViewById(R.id.custom_row_image);
        exercises_textView = constraintLayout.findViewById(R.id.custom_row_text);
    }

    public void setData(int rowLine) {
        exercises_imageView.setImageBitmap(null);
        exercises_textView.setText(Exercises_storage.displayedList.get(rowLine).getHeadLine());

    }



}