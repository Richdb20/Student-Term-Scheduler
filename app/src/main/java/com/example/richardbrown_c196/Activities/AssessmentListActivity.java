package com.example.richardbrown_c196.Activities;

import java.util.List;
import android.os.Bundle;
import android.view.View;
import java.util.Objects;
import android.content.Intent;
import com.example.richardbrown_c196.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.richardbrown_c196.Database.Repository;
import com.example.richardbrown_c196.Entities.AssessmentsEntity;


public class AssessmentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView recyclerView=findViewById(R.id.assessmentListView);
        Repository repo=new Repository(getApplication());
        List<AssessmentsEntity> assessments=repo.getAllAssessments();
        final AssessmentAdapter adapter=new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);
    }

    public void addToAssessmentList(View view) {
        Intent intent = new Intent(AssessmentListActivity.this, AssessmentDetailsActivity.class);
        startActivity(intent);
    }
}