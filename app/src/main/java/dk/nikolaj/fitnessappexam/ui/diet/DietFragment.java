package dk.nikolaj.fitnessappexam.ui.diet;
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

import dk.nikolaj.fitnessappexam.R;

public class DietFragment extends Fragment {

    private View v;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_diet, container, false);

        return v;
    }


    public void onResume() {
        super.onResume();

        getActivity().setTitle("Diet");
    }

}