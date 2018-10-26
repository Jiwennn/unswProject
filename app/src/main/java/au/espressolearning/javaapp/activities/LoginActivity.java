package au.espressolearning.javaapp.activities;
import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import au.espressolearning.javaapp.R;
import au.espressolearning.javaapp.UserRepository;
import au.espressolearning.javaapp.UserViewModel;
import au.espressolearning.javaapp.database.UserDAO;
import au.espressolearning.javaapp.database.UserRoomDatabase;
import au.espressolearning.javaapp.model.User;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText _emailText;
    private EditText _passwordText;
    private Button _loginButton;
    private TextView _signupLink;

    private UserDAO userDao;
    private UserRoomDatabase db;

    private static final String Tag = "Test";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = UserRoomDatabase.getInstance(this);

        _emailText = findViewById(R.id.input_email);
        _passwordText = findViewById(R.id.input_password);
        _loginButton = findViewById(R.id.btn_login);
        _signupLink = findViewById(R.id.link_signup);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        //user input
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }


        userDao = db.UserDao();
        Log.d(Tag,"Test 1111");


        if(userDao == null){
            Log.d(Tag,"userDao null");
        }else{
            Log.d(Tag,"userDao not null");
        }

        List<User> users = (List<User>) userDao.getAllUsers();
        User user = null;


        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getEmail().equals(email)){
                user = users.get(i);
            }
        }


        if(user == null){
            Toast.makeText(this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
            //create and initialize an intent
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{

            Toast.makeText(this, "Login is successful, hi " + user.getUsername(), Toast.LENGTH_SHORT).show();

            //create and initialize an intent
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

            //Create a Bundle Object
            Bundle extras = new Bundle();

            //Add key value pairs to this bundle
            extras.putString("name",user.getUsername());
            extras.putString("email",user.getEmail());
            extras.putString("password",user.getPwd());
            intent.putExtras(extras);

            startActivity(intent);
        }


        return valid;
    }

}