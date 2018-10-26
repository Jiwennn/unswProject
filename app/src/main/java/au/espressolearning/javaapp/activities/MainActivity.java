package au.espressolearning.javaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import au.espressolearning.javaapp.fragments.CourseFragment;
import au.espressolearning.javaapp.fragments.HomeFragment;
import au.espressolearning.javaapp.fragments.QuizFragment;
import au.espressolearning.javaapp.R;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import android.support.v4.content.res.ResourcesCompat;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private AHBottomNavigation bottomNavigation;

    private static final String Tag = "Test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = "";
        //top action bar
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (getIntent().hasExtra("name")) {
            username = getIntent().getStringExtra("name");
        }

        Log.d(Tag,"username is " + username);
        //bottom navigation
        mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.navigation);

        this.setupBottomNavigation();


        Bundle bundle = new Bundle();
        bundle.putString("namefromMain", username);
        // set Fragmentclass Arguments
        HomeFragment frag = new HomeFragment();
        frag.setArguments(bundle);

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerID, HomeFragment.newInstance());
        transaction.commit();

    }

    private void setupBottomNavigation()
    {
        //CREATE ITEMS
        AHBottomNavigationItem homeItem=new AHBottomNavigationItem(R.string.title_home, R.drawable.ic_home_black_24dp, R.color.white);
        AHBottomNavigationItem courseItem=new AHBottomNavigationItem(R.string.title_course, R.drawable.ic_library_books_black_24dp, R.color.white);
        AHBottomNavigationItem quizItem=new AHBottomNavigationItem(R.string.title_quiz, R.drawable.ic_assessment_black_24dp, R.color.white);
        //AHBottomNavigationItem profileItem=new AHBottomNavigationItem(R.string.title_profile, R.drawable.ic_notifications_black_24dp, R.color.white);

        //ADD PROPERTIES
        bottomNavigation.addItem(homeItem);
        bottomNavigation.addItem(courseItem);
        bottomNavigation.addItem(quizItem);
        //bottomNavigation.addItem(profileItem);

        //SET PROPERTIES
        bottomNavigation.setCurrentItem(0);
        // Manage titles

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#e43f3f"));
        bottomNavigation.setAccentColor(Color.parseColor("#ffffff"));
        bottomNavigation.setInactiveColor(Color.parseColor("#99FFFFFF"));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setTitleTextSizeInSp(12,12);
        bottomNavigation.setTitleTypeface(ResourcesCompat.getFont(this, R.font.rubik_regular));


        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Fragment selectedFragment = null;
                switch (position) {
                    case 0:
                        selectedFragment = HomeFragment.newInstance();
                        break;
                    case 1:
                        selectedFragment = CourseFragment.newInstance();
                        break;
                    case 2:
                        selectedFragment = QuizFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.containerID, selectedFragment);
                transaction.commit();
                return true;
            }
        });

    }


    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);


        MenuItem logoutOption = menu.findItem(R.id.logoutOption);

        logoutOption.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                return false;
            }
        });


        return true;
    }

}
