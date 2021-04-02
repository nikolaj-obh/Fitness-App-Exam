package dk.nikolaj.fitnessappexam.ui.profile;
/**
 * @author Anders
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.auth.AuthManager;

public class ProfileFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{


    private static final String TAG = "ProfileFragment";

    private View v;
    private Button btn_logout;
    private EditText et_weight, et_fat;
    private CheckBox checkbox_dailyTip;
    private int input_weight, input_fat;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private final AuthManager authManager = new AuthManager();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        et_weight = v.findViewById(R.id.profile_etv_weight);
        et_fat = v.findViewById(R.id.profile_etv_fat);

        btn_logout = v.findViewById(R.id.profile_btn_logout);
        btn_logout.setOnClickListener(this);
        checkbox_dailyTip = v.findViewById(R.id.profile_cb_dailyTip);
        checkbox_dailyTip.setOnCheckedChangeListener(this);

        boolean dailyTip = mSharedPreferences.getBoolean(getString(R.string.setting_daily_tip), true);
        checkbox_dailyTip.setChecked(dailyTip);

        dailyTip();

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_btn_logout:
                authManager.logout(getActivity());
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()){
            case R.id.profile_cb_dailyTip:
                mEditor.putBoolean(getString(R.string.setting_daily_tip), isChecked);
        }
        mEditor.apply();
    }

    private void dailyTip() {
        long tip_last_prompt = mSharedPreferences.getLong(getString(R.string.daily_tip_last_prompt), 0);
        boolean showTip = mSharedPreferences.getBoolean(getString(R.string.setting_daily_tip), true);
        if (tip_last_prompt < System.currentTimeMillis() - 86400000 && showTip){
            String title = (getString(R.string.daily_tip_title));
            String[] tipArray = getResources().getStringArray(R.array.daily_tip_array);
            List<String> tipList = Arrays.asList(tipArray);
            Random rand = new Random();
            int index = rand.nextInt(tipList.size());
            String message = tipList.get(index);
            final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setCancelable(true);
            alert.setTitle(title);
            alert.setMessage(message);
            alert.setNegativeButton(getString(R.string.daily_tip_buttonText), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alert.show();
            mEditor.putLong(getString(R.string.daily_tip_last_prompt), (System.currentTimeMillis()));
            mEditor.apply();
        }
    }

    //Saves user goals when leaving fragment
    @Override
    public void onPause() {
        super.onPause();
        if (!et_weight.getText().toString().equals("") && Integer.parseInt(et_weight.getText().toString()) != 0) {
            input_weight = Integer.parseInt(et_weight.getText().toString());
        } else {
            input_weight = 0;
        }
        if (!et_fat.getText().toString().equals("") && Integer.parseInt(et_fat.getText().toString()) != 0) {
            input_fat = Integer.parseInt(et_fat.getText().toString());
        } else {
            input_fat = 0;
        }
        mEditor.putInt(getString(R.string.usergoal_weight), input_weight);
        mEditor.putInt(getString(R.string.usergoal_fat), input_fat);
        mEditor.apply();
    }

    public void onResume() {
        super.onResume();
        input_weight = mSharedPreferences.getInt(getString(R.string.usergoal_weight), 0);
        input_fat = mSharedPreferences.getInt(getString(R.string.usergoal_fat), 0);
        if (input_weight != 0){
            et_weight.setText(String.valueOf(input_weight));
        }
        if (input_fat != 0) {
            et_fat.setText(String.valueOf(input_fat));
        }
        // Sets toolbar title to following string
        getActivity().setTitle("Profile");
    }
}