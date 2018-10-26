package au.espressolearning.javaapp.activities;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import au.espressolearning.javaapp.R;
import au.espressolearning.javaapp.UserRepository;
import au.espressolearning.javaapp.UserViewModel;
import au.espressolearning.javaapp.database.UserRoomDatabase;
import au.espressolearning.javaapp.model.User;

import java.util.List;

public class UserAreaActivity extends AppCompatActivity {

    //declare variables
    private TextView p_name;
    private TextView p_email;

    //add database
    private UserRoomDatabase db;

    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        //initialise db
        db = UserRoomDatabase.getInstance(this);

        p_name = findViewById(R.id.pName);
        p_email = (TextView) findViewById(R.id.pEmail);

        //userList = db.UserDao().getAllUsers();
    }

    //to clean up the reference and override the destroy method
    @Override
    protected void onDestroy() {
        UserRoomDatabase.destroyInstance();
        super.onDestroy();
    }
}