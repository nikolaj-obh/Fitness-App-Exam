package dk.nikolaj.fitnessappexam;
/**
 * @author Anders & Nikolaj
 */

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dk.nikolaj.fitnessappexam.ui.diet.DietFragment;
import dk.nikolaj.fitnessappexam.ui.exercises.CategoriesFragment;
import dk.nikolaj.fitnessappexam.ui.exercises.ExercisesFragment;
import dk.nikolaj.fitnessappexam.ui.profile.ProfileFragment;
import dk.nikolaj.fitnessappexam.ui.recipes.FoodCategoriesFragment;
import dk.nikolaj.fitnessappexam.ui.training.TrainingFragment;

public class MenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setSelectedItemId(R.id.navigation_profile);
        navigationView.setOnNavigationItemSelectedListener(this);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.navigation_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment(), "Profile_Fragment").commit();
                break;

            case R.id.navigation_diet:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DietFragment(), "Diet_Fragment").commit();
                break;

            case R.id.navigation_categories:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CategoriesFragment(), "Categories_Fragment").commit();
                break;

            case R.id.navigation_exercises:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ExercisesFragment(), "Exercises_Fragment").commit();
                break;

            case R.id.navigation_foodcategories:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FoodCategoriesFragment(), "FoodCategorie_Fragment").commit();
                break;

            case R.id.navigation_training:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrainingFragment(), "Training_Fragment").commit();
                break;

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        int counter = getFragmentManager().getBackStackEntryCount();
        if (counter == 0 ){
            super.onBackPressed();
        }else{
            getFragmentManager().popBackStack();
        }
    }
}