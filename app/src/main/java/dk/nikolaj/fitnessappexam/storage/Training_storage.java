package dk.nikolaj.fitnessappexam.storage;
/**
 * @author Anders & Osvald
 */

import java.util.ArrayList;
import java.util.Random;

import dk.nikolaj.fitnessappexam.adapter.TrainingAdapter;
import dk.nikolaj.fitnessappexam.model.TrainingModel;

public class Training_storage {
    private TrainingAdapter trainingAdapter = new TrainingAdapter();

    private static String category;

    private static ArrayList<TrainingModel> TrainingSchemaList = new ArrayList<>();
    private static ArrayList<TrainingModel> TrainingDayList = new ArrayList<>();
    private static ArrayList<TrainingModel> TrainingSchemaListDisplayed = new ArrayList<>();

    
    private static final String TAG = "Training_storage";

    public static void setCategory(String category) {
        Training_storage.category = category;
    }

    public static ArrayList<TrainingModel> getTrainingSchemaList() {
        return TrainingSchemaList;
    }

    public static ArrayList<TrainingModel> getTrainingDayList() {
        return TrainingDayList;
    }

    public static ArrayList<TrainingModel> getTrainingSchemaListDisplayed() {
        return TrainingSchemaListDisplayed;
    }

    public void sortList(){
        TrainingSchemaListDisplayed.clear();
        for (int i = 0; i< TrainingSchemaList.size(); i++){
            if (TrainingSchemaList.get(i).getCategory().contains(category)) {
               if (!TrainingSchemaList.get(i).getCategory().contains("Rest day")) {
                   TrainingSchemaListDisplayed.add(TrainingSchemaList.get(i));
               }
            }
        }
        ArrayList<TrainingModel> trainingTemp = new ArrayList<>();
        for (int i = 0; trainingTemp.size() < 8; i++) {
            Random rand = new Random();
            int r = rand.nextInt(TrainingSchemaListDisplayed.size());
            if (!trainingTemp.contains(TrainingSchemaListDisplayed.get(r))){
                trainingTemp.add(TrainingSchemaListDisplayed.get(r));
            }
        }
        TrainingSchemaListDisplayed.clear();
        TrainingSchemaListDisplayed.addAll(trainingTemp);
    }

}
