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
import dk.nikolaj.fitnessappexam.storage.Categories_storage;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    private final TextView categories_textView;
    private final ImageView categories_imageView;

    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        ConstraintLayout constraintLayout = (ConstraintLayout) itemView;
        categories_imageView = constraintLayout.findViewById(R.id.custom_row_image);
        categories_textView = constraintLayout.findViewById(R.id.custom_row_text);
    }

    public void setData(int rowLine) {
        Categories_storage cs = new Categories_storage();
        cs.downloadImage(Categories_storage.list.get(rowLine).getCategoryImg(),categories_imageView);
        categories_textView.setText(Categories_storage.list.get(rowLine).getHeadLine());

    }
}