package dk.nikolaj.fitnessappexam.storage;
/**
 * @author Anders & Osvald
 */

import java.util.ArrayList;

import dk.nikolaj.fitnessappexam.model.ExercisesModel;

public class Exercises_storage {

    private static String category;

    public static ArrayList<ExercisesModel> FullList = new ArrayList<>();
    public static ArrayList<ExercisesModel> displayedList = new ArrayList<>();

    private static final String TAG = "Exercise_storage";

    public static void setDisplayedList(ArrayList<ExercisesModel> displayedList) {
        Exercises_storage.displayedList = displayedList;
    }

    public static ArrayList<ExercisesModel> getDisplayedList() {
        return displayedList;
    }

    public static ArrayList<ExercisesModel> getFullList() {

        return FullList;
    }

    public String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        Exercises_storage.category = category;
    }

    //Clears list to be displayed, and adds to it from the full list, of conditions are met
    public void sortList() {
        displayedList.clear();

        for (int i = 0; i < FullList.size(); i++) {
            if (FullList.get(i).getCategory().equalsIgnoreCase(category)) {
                displayedList.add(FullList.get(i));
            }
        }
    }
}
