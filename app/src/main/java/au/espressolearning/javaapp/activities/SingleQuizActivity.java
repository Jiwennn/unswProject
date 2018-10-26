package au.espressolearning.javaapp.activities;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.io.IOException;
import java.io.InputStream;

import au.espressolearning.javaapp.Course;
import au.espressolearning.javaapp.Quiz;
import au.espressolearning.javaapp.R;
import au.espressolearning.javaapp.fragments.QuizFragment;

public class SingleQuizActivity extends AppCompatActivity {

    TextView quizText;
    TextView quizQn;
    ImageView qnImg;

    private Button mTrueButton;
    private Button mFalseButton;

    private Button mNextButton;

    private static Quiz[][] quiz = {{
            new Quiz("1-1", "1-1.png", "Select Option 1 if the output is Base::show() called? \nSelect Option 2 if the output is Derived::show() called?", false, "Topic 1 : Overview"),
            new Quiz("1-2", "1-2.png", "Select Option 1 if there is a compiler error. \nSelect Option 2 if there is a runtime error", true, "Topic 1 : Overview"),
            new Quiz("1-3", "1-3.png", "Select Option 1 if the output is Base::show() called? \nSelect Option 2 if the output is Derived::show() called?", false, "Topic 1 : Overview")
    }, {
            new Quiz("2-1", "2-1.png", "Select Option 1 if there is a runtime error. \nSelect Option 2 if there is a compiler error", false, "Topic 2 : Object & Classes"),
            new Quiz("2-2", "2-2.png", "Select Option 1 if there is a runtime error. \nSelect Option 2 if the output is 0 ", false, "Topic 2 : Object & Classes"),
            new Quiz("2-3", "2-3.png", "Select Option 1 if the output is values of obj1: \na = 11 b = 21\nvalues of obj2: \na = 11 b = 21. \nSelect Option 2 if the output is \na = 11 b = 21\nvalues of obj2: \na = 10 b = 20. ", true, "Topic 2 : Object & Classes")
    }, {
            new Quiz("3-1", "3-1.png", "What will be the output of the following program? \nSelect Option 1 if the output is  No Output. \nSelect Option 2 if there is a compiler error", false, "Topic 3 : Loop Control"),
            new Quiz("3-2", "3-2.png", "What will be the output of the following program? \nSelect Option 1 if the output is HELLO GEEKS. \nSelect Option 2 if the output is a compiler error", false, "Topic 3 : Loop Control"),
            new Quiz("3-3", "3-3.png", "What will be the output of the following program? \nSelect Option 1 if the output is HI. \nSelect Option 2 if the output is HELLO GEEKS", true, "Topic 3 : Loop Control")
    }, {
            new Quiz("4-1", "4-1.png", "What will be the output of the following class? \nSelect Option 1 if the output is hello. \nSelect Option 2 if the output is HELLO", true, "Topic 4 : Strings"),
            new Quiz("4-2", "4-2.png", "Select Option 1 if the output is  false, false. \nSelect Option 2 if the output is false, true", false, "Topic 4 : Strings"),
            new Quiz("4-3", "4-3.png", "What is the difference b/w String and StringBuilder? \nSelect Option 1 if the answer is String is immutable, StringBuilder is not. \nSelect Option 2 if the answer is StringBuilder is thread safe, String is not.", true, "Topic 4 : Strings")
    }, {
            new Quiz("5-1", "5-1.png", "Select Option 1 if the output is 10 20 30 40 50. \nSelect Option 2 if the output is 10 20 30 40", true, "Topic 5 : Arrays"),
            new Quiz("5-2", "5-2.png", "Select Option 1 if the output is 0 0. \nSelect Option 2 if the output is a compiler error", false, "Topic 5 : Arrays"),
            new Quiz("5-3", "5-3.png", "Select Option 1 if the output is 9\n7 8\n4 5 6\n0 1 2 3 . \nSelect Option 2 if the output is 0\n1 2\n3 4 5\n6 7 8 9 ", false, "Topic 5 : Arrays")
    }, {
            new Quiz("6-1", "5-1.png", "Select Option 1 if the output is 20. \nSelect Option 2 if the output is compiler error", false, "Topic 6 : Inner Classes"),
            new Quiz("5-2", "5-2.png", "Select Option 1 if the output is 0 0. \nSelect Option 2 if the output is a compiler error", false, "Topic 6 : Inner Classes"),
            new Quiz("5-2", "5-2.png", "Select Option 1 if the output is 0 0. \nSelect Option 2 if the output is a compiler error", false, "Topic 6 : Inner Classes")
    }};


    private int mCurrentIndex = 0;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_quiz);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getIncomingIntent();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        quizText = (TextView) findViewById(R.id.quiz_label);
        quizQn = (TextView) findViewById(R.id.quiz_qn);
        qnImg = (ImageView) findViewById(R.id.imageView3);

        mTrueButton = findViewById(R.id.true_1);
        mTrueButton.setOnClickListener(new View.OnClickListener() {

            @Override

            //In makeText(), QuizActivity is passed as the Context argument; this refers to the anonymous class View.OnClickListener
            public void onClick(View v) {
                checkAnswer(true);
            }
        });


        mFalseButton = findViewById(R.id.false_2);
        mFalseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });


        mNextButton = findViewById(R.id.next);

        /*
        if (count == (quiz[0].length - 1)) {
            mNextButton.setVisibility(View.GONE);
        }
*/
        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    mCurrentIndex = (mCurrentIndex + 1) % quiz.length;
                    nextQuestion();
                    count++;
            }
        });


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

    //both navigation bar back press and title bar back press will trigger this method
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("quiz_number")) {
            String quizName = getIntent().getStringExtra("quiz_number");
            String topicName = getIntent().getStringExtra("topic_name");

            setTopic("Quizzes " + " for " + topicName);
            TextView textView = findViewById(R.id.quiz_label);
            textView.setText("Quiz " + quizName);
        }
        if (getIntent().hasExtra("quiz_url")) {
            String imageQuiz = getIntent().getStringExtra("quiz_url");

            ImageView imageView = findViewById(R.id.imageView3);
            imageView.setImageBitmap(getBitmapFromAssets(imageQuiz));

        }
        if (getIntent().hasExtra("quiz_qn")) {
            String qn_2 = getIntent().getStringExtra("quiz_qn");

            TextView textView = findViewById(R.id.quiz_qn);
            textView.setText(qn_2);
        }

    }

    private void setTopic(String quizName) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(quizName);
    }

    // Custom method to get assets folder image as bitmap
    private Bitmap getBitmapFromAssets(String fileName) {
        AssetManager am = getAssets();
        InputStream is = null;
        try {
            is = am.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }

    private void nextQuestion() {
        //get topic id from QuizAdaper
        int t_id = Integer.parseInt(getIntent().getExtras().get("topic_id").toString()) - 1;

        String quizNumber = quiz[t_id][mCurrentIndex].getQuizID();
        String quizUrl = quiz[t_id][mCurrentIndex].getQuizImage();
        String quiz_Qn = quiz[t_id][mCurrentIndex].getQuizQn();
        quizText.setText("Question " + quizNumber);
        quizQn.setText(quiz_Qn);
        qnImg.setImageBitmap(getBitmapFromAssets(quizUrl));



    }

    //check if the user input's answer is right or wrong
    private void checkAnswer(boolean userPressedTrue) {
        String quizAns = getIntent().getStringExtra("quiz_ans");

        Boolean ans = Boolean.valueOf(quizAns);

        int messageResId = 0;
        if (userPressedTrue == ans) {
            messageResId = R.string.correct_Toast;

        } else {
            messageResId = R.string.inCorrect_Toast;

        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
