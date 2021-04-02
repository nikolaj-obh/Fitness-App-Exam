package dk.nikolaj.fitnessappexam.storage;
/**
 * @author Nikolaj
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import dk.nikolaj.fitnessappexam.model.Recipe;

public class Recipes_storage {

    static StorageReference storageReference;
    static FirebaseStorage storage = FirebaseStorage.getInstance();
    private static final String TAG = "Recipes_storage";
    public static ArrayList<Recipe> FullList = new ArrayList<>();
    public static ArrayList<Recipe> displayedList = new ArrayList<>();
    private static String category;

    public static ArrayList<Recipe> getDisplayedList() {
        return displayedList;
    }

    public static void setDisplayedList(ArrayList<Recipe> displayedList) {
        Recipes_storage.displayedList = displayedList;
    }

    public static ArrayList<Recipe> getFullList() {

        return FullList;
    }

    public void sortList() {
        displayedList.clear();

        Log.i(TAG, "Category; " + category);
        for (int i = 0; i < FullList.size(); i++) {
            if (FullList.get(i).getCategory().equalsIgnoreCase(category)) {
                displayedList.add(FullList.get(i));
            }
        }
    }

    public String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        Recipes_storage.category = category;
    }

    //Method for downloading an image from Firebase Storage and assign to imageView
    public static void downloadImage(String name, final ImageView iv) {
        // Get reference from file storage, given a file name
        storageReference = storage.getReference();
        StorageReference ref = storageReference.child("images/recipes/" + name + ".png");
        int max = 1024 * 1024 * 10; // 10 megabytes maximum
        ref.getBytes(max).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                iv.setImageBitmap(bm); // Set image data to imageView
                System.out.println("Downloaded image");
            }
        });
    }
}
