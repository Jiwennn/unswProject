package au.espressolearning.javaapp.activities;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import au.espressolearning.javaapp.R;
import au.espressolearning.javaapp.UserRepository;
import au.espressolearning.javaapp.helper.InputValidation;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    //declare variables
    private EditText r_name;
    private EditText r_email;
    private EditText r_pwd;
    private EditText r_confirmPWD;

    private Button r_Btn;

    public static final String EXTRA_REPLY = "com.example.android.userlistsql.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        r_confirmPWD = (EditText) findViewById(R.id.rConfirmPwd);
        r_name = (EditText) findViewById(R.id.rName);
        r_pwd = (EditText) findViewById(R.id.rPwd);
        r_email = (EditText) findViewById(R.id.rEmail);
        r_Btn = (Button) findViewById(R.id.registerBtn);

        r_Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                String password = r_pwd.getText().toString();
                String confirmPWD = r_confirmPWD.getText().toString();

                if (validate() && confirmPWD.equals(password)) {

                    // insert new user data
                    UserRepository userRepository = new UserRepository((Application) getApplicationContext());

                    String username = r_name.getText().toString();
                    String email = r_email.getText().toString();
                    String pwd = r_pwd.getText().toString();

                    //Create a Bundle Object
                    Bundle extras = new Bundle();

                    //Add key value pairs to this bundle
                    extras.putString("name", username);
                    extras.putString("email", email);
                    extras.putString("password", pwd);

                    //create and initialize an intent
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

                    intent.putExtras(extras);

                    startActivity(intent);
                } else {
                    // display an error message when the validation is unsuccessfully
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RegisterActivity.this);

                    dlgAlert.setMessage(" Please check the detail again.");
                    dlgAlert.setTitle("Registration is unsuccessful!");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });


                }
            }

        });
    }

    public boolean validate() {
        boolean valid = true;

        String username = r_name.getText().toString();
        String email = r_email.getText().toString();
        String password = r_pwd.getText().toString();
        String confirmPWD = r_confirmPWD.getText().toString();

        if (username.isEmpty()) {
            r_email.setError("username is empty");
            valid = false;
        } else {
            r_email.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            r_email.setError("enter a valid email address");
            valid = false;
        } else {
            r_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            r_pwd.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            r_pwd.setError(null);
        }

        if (confirmPWD.isEmpty()) {
            r_confirmPWD.setError("cannot be empty");
            valid = false;
        } else {
            r_confirmPWD.setError(null);
        }

        return valid;
    }
}
