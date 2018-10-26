package au.espressolearning.javaapp.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import au.espressolearning.javaapp.R;

import static android.content.ContentValues.TAG;

public class SingleCourseActivity extends AppCompatActivity  implements YouTubePlayer.OnInitializedListener{
    static final String GOOGLE_API_KEY = "AIzaSyBQ5s1u4Skm6A1MW9KbCg4k67VUixUFvR4";
    static String YOUTUBE_VIDEO_ID = null;
    private YouTubePlayerSupportFragment youTubePlayerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getIncomingIntent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Title bar back press triggers onBackPressed()
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Both navigation bar back press and title bar back press will trigger this method
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ) {
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("course_name")) {
            String courseName = getIntent().getStringExtra("course_name");

            setTopic(courseName);
            TextView textView = findViewById(R.id.sub_text);
            textView.setText(courseName);
        }
        if (getIntent().hasExtra("course_description")) {
            String courseDescription = getIntent().getStringExtra("course_description");

            TextView textView = findViewById(R.id.supporting_text);
            textView.setText(courseDescription);

        }
        if (getIntent().hasExtra("course_url")) {
            YOUTUBE_VIDEO_ID = getIntent().getStringExtra("course_url");

            //YouTubePlayerView video = findViewById(R.id.videoView);

            // Initializing YouTube player view
            //YouTubePlayerView playerView = findViewById(R.id.videoView);
            //playerView.initialize(GOOGLE_API_KEY, this);
            youTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.youtube_player_fragment);


            youTubePlayerFragment.initialize(GOOGLE_API_KEY, this);
        }
    }

    private void setTopic(String courseName) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(courseName);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG,"onInitializationSuccess: provider is "  + provider.getClass().toString());
        Toast.makeText(this,"Initialized YouTube Player successfully", Toast.LENGTH_LONG).show();

        if(!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if(youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YoutubePlayer (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    /*
    Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrowser(view);
            }
        });
    }

    public void openBrowser(View view){
        EditText editText = findViewById(R.id.editText);

        String url = editText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        startActivityForResult(intent,1);
        //finish();
        //onActivityResult(1,RESULT_OK,intent);
    }

     */

}
