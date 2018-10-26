package au.espressolearning.javaapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import au.espressolearning.javaapp.fragments.CourseFragment;
import au.espressolearning.javaapp.fragments.HomeFragment;
import au.espressolearning.javaapp.fragments.ProfileFragment;
import au.espressolearning.javaapp.fragments.QuizFragment;
import au.espressolearning.javaapp.R;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import android.support.v4.content.res.ResourcesCompat;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //top action bar
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        //bottom navigation
        mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.navigation);
        /*
        navigation.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.homeTab:
                                selectedFragment = HomeFragment.newInstance();
                                break;
                            case R.id.courseTab:
                                selectedFragment = CourseFragment.newInstance();
                                break;
                            case R.id.quizTab:
                                selectedFragment = QuizFragment.newInstance();
                                break;
                            case R.id.profileTab:
                                selectedFragment = ProfileFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.containerID, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
*/
        this.setupBottomNavigation();

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
        AHBottomNavigationItem profileItem=new AHBottomNavigationItem(R.string.title_profile, R.drawable.ic_notifications_black_24dp, R.color.white);

        //ADD PROPERTIES
        bottomNavigation.addItem(homeItem);
        bottomNavigation.addItem(courseItem);
        bottomNavigation.addItem(quizItem);
        bottomNavigation.addItem(profileItem);

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
                    case 3:
                        selectedFragment = ProfileFragment.newInstance();
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
        return true;
    }

}
