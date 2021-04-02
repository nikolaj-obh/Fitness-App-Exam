package dk.nikolaj.fitnessappexam.ui.signup;
/**
 * @author Osvald
 */
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dk.nikolaj.fitnessappexam.R;
import dk.nikolaj.fitnessappexam.auth.AuthManager;

public class CreateNewUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email_input, password_input, confirm_password_input;
    private Button sign_up;


    private AuthManager mAuthManager = new AuthManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        email_input = (EditText)findViewById(R.id.new_user_input_email);
        password_input = (EditText)findViewById(R.id.new_user_input_password);
        confirm_password_input = (EditText)findViewById(R.id.new_user_input_passwordConf);
        sign_up = findViewById(R.id.new_user_btn_signUp);
        sign_up.setOnClickListener(this);
    }

    public void visibel(){
        TextView verify_text;
        verify_text = findViewById(R.id.new_user_popup_verify);
        verify_text.setVisibility(View.VISIBLE);
    }


    public void comparePassword() {
        String email_value = email_input.getText().toString();
        String password_value = password_input.getText().toString();
        String confirm_password_value = confirm_password_input.getText().toString();
        if (email_value != null && !email_value.isEmpty() &&
            password_value != null && !email_value.isEmpty() &&
            confirm_password_value != null && !confirm_password_value.isEmpty()){
            if (password_value.equals(confirm_password_value)){
                mAuthManager.signUp(email_value, password_value, this, this);
            }else {
                Toast.makeText(this, "Passwords isn't equals to eachother", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "All fields should be filled out", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_user_btn_signUp:
                comparePassword();
                break;
        }
    }
}