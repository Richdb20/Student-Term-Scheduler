package com.example.richardbrown_c196.Activities;

import java.util.List;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import com.example.richardbrown_c196.R;
import androidx.recyclerview.widget.RecyclerView;
import com.example.richardbrown_c196.Entities.AssessmentsEntity;


public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;
        private AssessmentViewHolder (View itemView) {
            super (itemView);
            assessmentItemView=itemView.findViewById(R.id.assessmentTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final AssessmentsEntity currentPosition=mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetailsActivity.class);
                    intent.putExtra("id", currentPosition.getAssessmentID());
                    intent.putExtra("courseID", currentPosition.getCourseID());
                    intent.putExtra("name", currentPosition.getAssessmentName());
                    intent.putExtra("startDate", currentPosition.getStartDate());
                    intent.putExtra("endDate", currentPosition.getEndDate());
                    intent.putExtra("type", currentPosition.getAssessmentType());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<AssessmentsEntity> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;
    public AssessmentAdapter (Context context) {
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View assessmentItemView=mInflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentAdapter.AssessmentViewHolder(assessmentItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(mAssessments!=null) {
            AssessmentsEntity current=mAssessments.get(position);
            String name=current.getAssessmentName();
            holder.assessmentItemView.setText(name);
        }
        else{
            holder.assessmentItemView.setText("No Assessment Name");
        }
    }

    public void setAssessments(List<AssessmentsEntity> assessments) {
        mAssessments=assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAssessments != null) {
            return mAssessments.size();
        }
        else return 0;
    }
}
