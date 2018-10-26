package au.espressolearning.javaapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import au.espressolearning.javaapp.Course;
import au.espressolearning.javaapp.R;
import au.espressolearning.javaapp.activities.SingleCourseActivity;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.RecyclerVH> {

    private Context mContext;
    private Course[] courses;

    public CourseAdapter(Context mContext, Course[] courses) {
        this.mContext = mContext;
        this.courses = courses;
    }

    @Override
    public RecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.course_item_row,parent,false);
        return new RecyclerVH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerVH holder, final int position) {
        final String courseName = courses[position].getTopicName();
        final String courseDescrition = courses[position].getDescription();
        final String courseUrl = courses[position].getUrl();

        holder.courseText.setText(courseName);
        holder.courseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(mContext, "#" + (position+1) + " - " + courseName, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(mContext, SingleCourseActivity.class);

                    //retrieve course related data from database
                    intent.putExtra("course_name", courseName);
                    intent.putExtra("course_description", courseDescrition);
                    intent.putExtra("course_url",courseUrl);
                    mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.length;
    }


    /*
    VIEWHOLDER CLASS
     */
    public class RecyclerVH extends RecyclerView.ViewHolder
    {
        TextView courseText;
        CardView courseCard;

        public RecyclerVH(View itemView) {
            super(itemView);

            courseText = (TextView) itemView.findViewById(R.id.courseText);
            courseCard = (CardView) itemView.findViewById(R.id.courseCard);
        }
    }
}
