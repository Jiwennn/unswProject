package au.espressolearning.javaapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import au.espressolearning.javaapp.R;
import au.espressolearning.javaapp.Quiz;
import au.espressolearning.javaapp.activities.SingleQuizActivity;

public class SubQuizAdapter extends RecyclerView.Adapter<SubQuizAdapter.RecyclerVH> {

    private Context c;
    private Quiz[][] questions;

    public SubQuizAdapter(Context c, Quiz[][] questions) {
        this.c = c;
        this.questions = questions;
    }

    @Override
    public RecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.quiz_item_row,parent,false);
        return new RecyclerVH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerVH holder, final int position) {
        final String quizNumber = questions[position][0].getQuizID();
        final String quizUrl = questions[position][0].getQuizImage();
        final String quiz_Qn = questions[position][0].getQuizQn();
        final Boolean quiz_answer = questions[position][0].getAnswer();
        final String topicName = questions[position][0].getTopicName();

        //display on the quiz_item_row in QuizFragment
        holder.quizText.setText("Quiz for " +topicName);

        holder.quizCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int topicID = position + 1;
                Toast.makeText(c, "#" + topicID + " - Quiz for" + topicName, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(c, SingleQuizActivity.class);

                intent.putExtra("topic_id", topicID);
                intent.putExtra("quiz_number", quizNumber);
                intent.putExtra("quiz_url", quizUrl);
                intent.putExtra("quiz_qn", quiz_Qn);
                intent.putExtra("quiz_ans", quiz_answer);
                intent.putExtra("topic_name",topicName);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.length;
    }


    /*
    VIEWHOLDER CLASS
     */

    public class RecyclerVH extends RecyclerView.ViewHolder
    {
        TextView quizText;
        CardView quizCard;

        public RecyclerVH(View itemView) {
            super(itemView);

            quizText = (TextView) itemView.findViewById(R.id.quizText);
            quizCard = (CardView) itemView.findViewById(R.id.quizCard);
        }
    }
}

/*import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import au.espressolearning.javaapp.Quiz;
import au.espressolearning.javaapp.R;
import au.espressolearning.javaapp.fragments.QuizFragment;

public class SubQuizAdapter extends RecyclerView.Adapter<SubQuizAdapter.RecyclerVHSub>  {


    private Context cSub;
    private Quiz[][] questions;

    public SubQuizAdapter(Context cSub, Quiz[][] questions) {
        this.cSub = cSub;
        this.questions = questions;
    }

    @NonNull
    @Override
    public RecyclerVHSub onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVHSub recyclerVHSub, int i) {

        int t_id = Integer.parseInt(QuizFragment.getIntent().getExtras().get("topic_id").toString());
            final String quizNumber = questions[recyclerVHSub][i].getQuizID();
            final String quizUrl = questions[t_id][i].getQuizImage();
            final String quiz_Qn = questions[t_id][i].getQuizQn();
            final Boolean quiz_answer = questions[t_id][i].getAnswer();
            final String topicName = questions[t_id][i].getTopicName();

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecyclerVHSub extends RecyclerView.ViewHolder
    {
        TextView quizText;
        TextView quizQn;
        ImageView qnImg;
        CardView quizTCard;
        CardView qnCard;

        public RecyclerVHSub(View itemView) {
            super(itemView);


            quizText = (TextView) itemView.findViewById(R.id.quiz_label);
            quizQn = (TextView) itemView.findViewById(R.id.quiz_qn);
            qnImg = (ImageView) itemView.findViewById(R.id.imageView3);
            quizTCard = (CardView) itemView.findViewById(R.id.quizT_card);
            qnCard = (CardView) itemView.findViewById(R.id.qn_card);

        }
    }
}
*/
