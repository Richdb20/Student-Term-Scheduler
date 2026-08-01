package com.example.richardbrown_c196.Activities;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import com.example.richardbrown_c196.R;
import androidx.appcompat.app.AppCompatActivity;
import com.example.richardbrown_c196.Database.Repository;
import com.example.richardbrown_c196.Entities.AssessmentsEntity;
import com.example.richardbrown_c196.Entities.CoursesEntity;
import com.example.richardbrown_c196.Entities.TermsEntity;


public class MainActivity extends AppCompatActivity {

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Terms(View view) {
        Intent toTermList=new Intent(MainActivity.this, TermListActivity.class);
        startActivity(toTermList);
        Repository termRepo= new Repository(getApplication());
        TermsEntity termEntity=new TermsEntity(1, "Term 1", "1/1/2022", "5/1/2022");
        termRepo.insert(termEntity);
    }

    public void Courses(View view) {
        Intent toCourseList=new Intent(MainActivity.this, CourseListActivity.class);
        startActivity(toCourseList);
        Repository coursesRepo= new Repository(getApplication());
        CoursesEntity coursesEntity=new CoursesEntity(1, 15,"Software Development", "1/1/2022", "3/1/2022","Example Note", "active", "Bob", "623-888-2020", "bob@stuff.com");
        coursesRepo.insert(coursesEntity);
    }

    public void Assessments(View view) {
        Intent toAssessmentList=new Intent(MainActivity.this, AssessmentListActivity.class);
        startActivity(toAssessmentList);
        Repository assessmentsRepo= new Repository(getApplication());
        AssessmentsEntity assessmentsEntity=new AssessmentsEntity(1, 1, "OBJ1","1/1/2022", "3/1/2022", "Performance Assessment");
        assessmentsRepo.insert(assessmentsEntity);
    }
}