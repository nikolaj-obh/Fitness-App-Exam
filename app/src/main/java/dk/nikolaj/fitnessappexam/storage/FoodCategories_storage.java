package dk.nikolaj.fitnessappexam.storage;
/**
 * @author Nikolaj
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import dk.nikolaj.fitnessappexam.model.FoodCategories;

public class FoodCategories_storage {

    public static ArrayList<FoodCategories> list = new ArrayList<>();
    static StorageReference storageReference;
    static FirebaseStorage storage = FirebaseStorage.getInstance();

    public static ArrayList<FoodCategories> getList() {

        return list;
    }

    //Method for downloading an image from Firebase Storage and assign to imageView
    public static void downloadImage(String name, final ImageView iv) {
        // Get reference from file storage, given a file name
        storageReference = storage.getReference();
        StorageReference ref = storageReference.child("images/foodCategories/" + name + ".jpg");
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
