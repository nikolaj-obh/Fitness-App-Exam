package dk.nikolaj.fitnessappexam.storage;
/**
 * @author Anders & Osvald
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import dk.nikolaj.fitnessappexam.model.CategoriesModel;

public class Categories_storage {

    static StorageReference storageReference;
    static FirebaseStorage storage = FirebaseStorage.getInstance();

    public static ArrayList<CategoriesModel> list = new ArrayList<>();


    public static ArrayList<CategoriesModel> getList() {

        return list;
    }

    public static void downloadImage(String name, final ImageView iv) {
        // Get reference from file storage, given a file name
        storageReference = storage.getReference();
        StorageReference ref = storageReference.child("images/exercises_cate_pics/" + name + ".jpg");
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
