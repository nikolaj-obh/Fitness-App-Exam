package dk.nikolaj.fitnessappexam.ui.progression;
/**
 * @author Anders
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

import dk.nikolaj.fitnessappexam.R;

public class ProgressionFragment extends Fragment implements View.OnClickListener {

    private View v;
    private Button clearDataBtn;
    private FloatingActionButton addBtn;

    private EditText date, data;
    private String dateInput, dataInput, selectedTab = "Weight";
    private ArrayList<Entry> entryListY = new ArrayList<>();
    private ArrayList<String> entryListX = new ArrayList<>();

    private static final String TAG = "ProgressionFragment";

    private LineChart mChart;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private float userGoal;
    private Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_progression, container, false);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        addBtn = v.findViewById(R.id.progression_floatingButton);
        addBtn.setOnClickListener(this);
        clearDataBtn = v.findViewById(R.id.progression_btn_clear);
        clearDataBtn.setOnClickListener(this);

        mChart = v.findViewById(R.id.progression_chart);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);


        drawGraph();

        TabLayout tabLayout = v.findViewById(R.id.progression_tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    selectedTab = "Weight";
                    mChart.clear();
                    drawGraph();
                }else if (tab.getPosition() == 1){
                    selectedTab = "Fat";
                    mChart.clear();
                    drawGraph();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

    private void drawGraph(){
        //Fetch user goal
        if (selectedTab.equals("Weight")) {
            userGoal = (float) mSharedPreferences.getInt(getString(R.string.usergoal_weight), 0);
        }else{
            userGoal = (float) mSharedPreferences.getInt(getString(R.string.usergoal_fat),0);
        }

        //Line showing the users goal
        if (userGoal != 0) {
            LimitLine goal = new LimitLine(userGoal, "Goal, " + userGoal);
            goal.setLineWidth(4f);
            goal.enableDashedLine(15f, 15f, 0f);
            goal.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
            goal.setTextSize(15f);

            //Showing the goal line on the chart
            YAxis leftAxis = mChart.getAxisLeft();
            leftAxis.removeAllLimitLines();
            leftAxis.addLimitLine(goal);
            leftAxis.enableAxisLineDashedLine(10f, 10f, 0);
            leftAxis.setDrawLimitLinesBehindData(true);
        }

        //Y Points
        entryListY = loadEntryPointsY();
        LineDataSet set1 = new LineDataSet(entryListY, selectedTab);
        set1.setFillAlpha(85);

        //Line color between points
        set1.setColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setValueTextSize(11f);
        set1.setValueTextColor(Color.BLACK);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        mChart.getAxisRight().setEnabled(false);
        mChart.getDescription().setEnabled(false);
        mChart.setData(data);

        XAxis xAxis = mChart.getXAxis();
        entryListX = loadEntryPointsX();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(entryListX));
        xAxis.setGranularity(1f);
    }

    private ArrayList<String> loadEntryPointsX() {
        ArrayList<String> listX;
        String jsonList = "";
        Type type = new TypeToken<ArrayList<String>>() {}.getType();

        if (selectedTab.equals("Weight")){
            Log.i(TAG, "loadEntryPointsX: Loading Weight x");
            jsonList = mSharedPreferences.getString(getString(R.string.progression_weight_x), "");
        }else if (selectedTab.equals("Fat")){
            Log.i(TAG, "loadEntryPointsX: Loading Fat x");
            jsonList = mSharedPreferences.getString(getString(R.string.progression_fat_x), "");
        }
        listX = gson.fromJson(jsonList, type);
        if (listX != null){
            return listX;
        }else{
            ArrayList<String> list = new ArrayList<>();
            return list;
        }
    }

    private ArrayList<Entry> loadEntryPointsY() {
        ArrayList<Entry> listY;
        String jsonList = "";
        Type type = new TypeToken<ArrayList<Entry>>() {}.getType();

        if (selectedTab.equals("Weight")){
            Log.i(TAG, "loadEntryPointsX: Loading Weight y");
            jsonList = mSharedPreferences.getString(getString(R.string.progression_weight_y), "");
        }else if (selectedTab.equals("Fat")){
            Log.i(TAG, "loadEntryPointsX: Loading Fat y");
            jsonList = mSharedPreferences.getString(getString(R.string.progression_fat_y), "");
        }
        listY = gson.fromJson(jsonList, type);
        if (listY != null){
            return listY;
        }else{
            ArrayList<Entry> list = new ArrayList<>();
            return list;
        }
    }

    //Prompts alert dialog in which user can input new entry with date and weight/fat input
    private void addDataPoint() {
        date = new EditText(getActivity());
        date.setHint("Date");
        date.setInputType(InputType.TYPE_CLASS_DATETIME);
        data = new EditText(getActivity());
        data.setHint(selectedTab);
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(date);
        ll.addView(data);
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Add Entry")
                .setMessage("Input Date & " + selectedTab)
                .setView(ll)
                .setPositiveButton("Add Entry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dateInput = date.getText().toString();
                        dataInput = data.getText().toString();
                        saveDataPoint(dateInput, dataInput);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

        dialog.show();
    }

    private void saveDataPoint(String date, String data){
        if (date == null || date.equals("") || data == null || data.equals("")){
            Toast.makeText(getActivity(), "Please fill out both fields", Toast.LENGTH_SHORT).show();
            return;
        }

        float input = Float.parseFloat(data);
        int index = entryListY.size();
        entryListY.add(new Entry(index, input));

        index = entryListX.size();
        entryListX.add(index, date);

        String jsonY = gson.toJson(entryListY);
        String jsonX = gson.toJson(entryListX);
        if (selectedTab.equals("Weight")) {
            mEditor.putString(getString(R.string.progression_weight_y), jsonY);
            mEditor.putString(getString(R.string.progression_weight_x), jsonX);
        }else if (selectedTab.equals("Fat")){
            mEditor.putString(getString(R.string.progression_fat_y), jsonY);
            mEditor.putString(getString(R.string.progression_fat_x), jsonX);
        }
        mEditor.apply();
        drawGraph();
    }

    private void clearData() {
        if (selectedTab.equals("Weight")){
            mEditor.remove(getString(R.string.progression_weight_y));
            mEditor.remove(getString(R.string.progression_weight_x));
        }else if (selectedTab.equals("Fat")){
            mEditor.remove(getString(R.string.progression_fat_y));
            mEditor.remove(getString(R.string.progression_fat_x));
        }
        mEditor.apply();
        mChart.clear();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.progression_floatingButton:
                addDataPoint();
                break;

            case R.id.progression_btn_clear:
                clearData();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setTitle("Progression");
    }

}
