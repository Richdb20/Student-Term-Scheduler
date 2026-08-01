package com.example.richardbrown_c196.Activities;

import java.util.List;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import com.example.richardbrown_c196.R;
import androidx.recyclerview.widget.RecyclerView;
import com.example.richardbrown_c196.Entities.CoursesEntity;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;

        private CourseViewHolder(View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.courseTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final CoursesEntity currentPosition = mCourses.get(position);
                    Intent intent = new Intent(context, CourseDetailsActivity.class);
                    intent.putExtra("id", currentPosition.getCourseID());
                    intent.putExtra("termID", currentPosition.getTermID());
                    intent.putExtra("name", currentPosition.getCourseName());
                    intent.putExtra("startDate", currentPosition.getStartDate());
                    intent.putExtra("endDate", currentPosition.getEndDate());
                    intent.putExtra("courseNote", currentPosition.getCourseNote());
                    intent.putExtra("status", currentPosition.getCourseStatus());
                    intent.putExtra("instructorName", currentPosition.getMentorName());
                    intent.putExtra("instructorPhone", currentPosition.getMentorPhone());
                    intent.putExtra("instructorEmail", currentPosition.getMentorEmail());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<CoursesEntity> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;
    public CourseAdapter (Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItemView=mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseAdapter.CourseViewHolder(courseItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if(mCourses!=null) {
            CoursesEntity current=mCourses.get(position);
            String title=current.getCourseName();
            holder.courseItemView.setText(title);
        }
        else{
            holder.courseItemView.setText("No Course Name");
        }
    }

    public void setCourses(List<CoursesEntity> courses) {
        mCourses=courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mCourses != null) {
            return mCourses.size();
        }
        else return 0;
    }
}
