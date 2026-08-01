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
import com.example.richardbrown_c196.Entities.CoursesEntity;


public class CourseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView recyclerView=findViewById(R.id.courseListView);
        Repository repo=new Repository(getApplication());
        List<CoursesEntity> courses=repo.getAllCourses();
        final CourseAdapter adapter=new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(courses);
    }

    public void addToCourseList(View view) {
        Intent intent = new Intent(CourseListActivity.this, CourseDetailsActivity.class);
        startActivity(intent);
    }
}