package dk.nikolaj.fitnessappexam.ui.login;
/**
 * @author Anders, Nikolaj & Osvald
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.auth.AuthManager;
import dk.nikolaj.fitnessappexam.ui.signup.CreateNewUserActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private CallbackManager mCallbackManager;
    private FirebaseAuth mFirebaseAuth;
    private static final int RC_SIGN_IN = 123;
    private LoginButton fbLoginButton;
    private SignInButton googleLoginButton;
    private Button signUpBtn;
    private GoogleSignInClient mGoogleSignInClient;
    private AuthManager authManager = new AuthManager();

    private Button btnLogin;
    private String email, password;
    private EditText inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createGoogleRequest();

        mFirebaseAuth = FirebaseAuth.getInstance();
        authManager.checkLoginStatus();

        inputEmail = findViewById(R.id.login_editText_email);
        inputPassword = findViewById(R.id.login_editText_password);

        btnLogin = findViewById(R.id.login_btn_logIn);
        btnLogin.setOnClickListener(this);
        signUpBtn = findViewById(R.id.login_btn_signUp);
        signUpBtn.setOnClickListener(this);
        fbLoginButton = findViewById(R.id.login_btn_facebook);
        googleLoginButton = findViewById(R.id.login_btn_google);
        googleLoginButton.setOnClickListener(this);
        customizeGoogleLoginButton(googleLoginButton);
        mCallbackManager = CallbackManager.Factory.create();
        fbLoginButton.setReadPermissions("email", "public_profile");

        fbLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i(TAG, "FB onSuccess; " + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    private void createGoogleRequest() {
        //Configure Google Sign in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Used for customizing the google login button, such as changing the text
    //Found online at; https://stackoverflow.com/questions/43999960/customize-google-signinbutton-in-android
    public static void customizeGoogleLoginButton(SignInButton signInButton) {
        for (int i = 0; i < signInButton.getChildCount(); i++)
        {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView)
            {
                TextView tv = (TextView) v;
                //here you can customize what you want
                tv.setText("login with google");
                tv.setAllCaps(true);
                return;
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void fetchInput() {
        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_google:
                googleSignIn();
                break;

            case R.id.login_btn_logIn:
                fetchInput();
                if (email.length() > 0 && password.length() > 0) {
                    authManager.emailSignIn(email, password, this);
                } else {
                    Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.login_btn_signUp:
                redirectToCreateUser();
                break;
        }
    }

    private void handleFacebookToken(AccessToken token) {
        Log.i(TAG, "handleFBTOKEN; " + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.i(TAG, "Successful FB login");
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                } else {
                    Log.i(TAG, "Unsuccessful FB login");
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void redirectToCreateUser() {
        Intent intent = new Intent(MainActivity.this, CreateNewUserActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        authManager = new AuthManager();
        authManager.setupAuthStateListener(this);
    }
}
