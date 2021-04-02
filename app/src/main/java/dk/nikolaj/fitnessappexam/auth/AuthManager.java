package dk.nikolaj.fitnessappexam.auth;
/**
 * @author Anders
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dk.nikolaj.fitnessappexam.MenuActivity;
import dk.nikolaj.fitnessappexam.firebaseHandler.FirebaseHandler;
import dk.nikolaj.fitnessappexam.ui.login.MainActivity;

public class AuthManager {

    private static final String TAG = "AuthManager";

    FirebaseAuth auth;

    private Context mContext;
    private Activity mActivity;
    private final FirebaseHandler firebaseHandler = new FirebaseHandler();

    private boolean isLoggedIn = false;

    //Constructor which assign value to auth
    public AuthManager() {
        auth = FirebaseAuth.getInstance();
    }

    //Listener for knowing if a user is logged in or not (recognised in the list of users on firebase)
    public void setupAuthStateListener(final MainActivity activity) {
        auth.addIdTokenListener(new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null && isLoggedIn == false){
                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                        isLoggedIn = true;
                        auth.removeIdTokenListener(this);
                        firebaseHandler.loadDB();
                        Log.i(TAG, "Signed in from firebase");
                        Intent intent = new Intent(activity, dk.nikolaj.fitnessappexam.MenuActivity.class);
                        activity.finish();
                        activity.startActivity(intent);
                    }else{
                       // auth.signOut();
                        isLoggedIn = false;
                        Toast.makeText(activity, "Your email is not verified. Verify the email to log in.", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onIdTokenChanged: Email not verified");
                    }
                }else {
                    isLoggedIn = false;
                    Log.i(TAG, "Signed out of firebase");
                }
            }
        });
    }

    //Method for signing in an existing user
    public void emailSignIn(String email, String pwd, final MainActivity activity) {
        auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //If login is successful write it in log, start new intent for LogOverview class, and finish MainActivity so user can't return to login screen without logging out
                    Log.i(TAG, "Sign in with email & password successful");
                } else {
                    //If login failed write exception in logs
                    Log.i(TAG, "Error; Sign in with email & password unsuccessful " + task.getException());
                }
            }
        });
    }

    //Method for signing up a new user
    public void signUp(String email, String pwd, Context context, Activity activity){
        //Listener which gives response when the createUser method has run
        mContext = context;
        mActivity = activity;
        auth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null) {
                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.i(TAG, "Verification Email sent.");
                                            }
                                        }
                                    });
                            auth.signOut();
                            }

                        } else {
                            Toast.makeText(mContext, "Sign-up failed.", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: " + task.getException());
                        }
                    }

                });
    }

    public void checkLoginStatus(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            if (!firebaseAuth.getCurrentUser().isEmailVerified()){
                firebaseAuth.signOut();
            }
        }
    }

    //Method for logging out the user
    public void logout(Activity activity) {
        Log.i(TAG, "logout button pressed");
        auth.signOut();
        Intent intent = new Intent(activity, MainActivity.class);
        activity.finish();
        activity.startActivity(intent);
    }

}
