package com.example.richardbrown_c196.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.richardbrown_c196.Entities.AssessmentsEntity;
import com.example.richardbrown_c196.Entities.CoursesEntity;
import com.example.richardbrown_c196.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.richardbrown_c196.Database.Repository;
import com.example.richardbrown_c196.Entities.TermsEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class TermDetailsActivity extends AppCompatActivity {

    EditText termNameText;
    EditText termStartDateText;
    EditText termEndDateText;
    String termName;
    String termStart;
    String termEnd;
    String dateFormat;
    EditText termStartDate;
    EditText termEndDate;
    Repository repository;
    TermsEntity currentTerm;
    SimpleDateFormat simpleDateFormat;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar calendarStartDate = Calendar.getInstance();
    final Calendar calendarEndDate = Calendar.getInstance();
    int numCourses;
    int termID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        termID=getIntent().getIntExtra("id", -1);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView recyclerView=findViewById(R.id.associatedCourseListView);
        Repository repo=new Repository(getApplication());
        List<CoursesEntity> courses=repo.getAllTermCourses(termID);
        final CourseAdapter adapter=new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(courses);

        termNameText=findViewById(R.id.termName);
        termStartDate=findViewById(R.id.termStartDate);
        termEndDate=findViewById(R.id.termEndDate);
        termName=getIntent().getStringExtra("name");
        termStart=getIntent().getStringExtra("startDate");
        termEnd=getIntent().getStringExtra("endDate");
        termNameText.setText(termName);
        termStartDate.setText(termStart);
        termEndDate.setText(termEnd);
        repository = new Repository(getApplication());


        dateFormat = "MM/dd/yy";
        simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

        termStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String start = termStartDate.getText().toString();
                if (start.equals("")) start = "02/22/2022";
                try {
                    calendarStartDate.setTime(simpleDateFormat.parse(start));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetailsActivity.this, startDate, calendarStartDate.get
                        (Calendar.YEAR), calendarStartDate.get(Calendar.MONTH), calendarStartDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarStartDate.set(Calendar.YEAR, year);
                calendarStartDate.set(Calendar.MONTH, month);
                calendarStartDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateStartLabel();
            }
        };

        termEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String end = termEndDate.getText().toString();
                if (end.equals("")) end = "02/22/2022";
                try {
                    calendarEndDate.setTime(simpleDateFormat.parse(end));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetailsActivity.this, endDate, calendarEndDate.get
                        (Calendar.YEAR), calendarEndDate.get(Calendar.MONTH), calendarEndDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarEndDate.set(Calendar.YEAR, year);
                calendarEndDate.set(Calendar.MONTH, month);
                calendarEndDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEndLabel();
            }
        };
    }

    private void updateStartLabel() {
        termStartDate.setText(simpleDateFormat.format(calendarStartDate.getTime()));
    }

    private void updateEndLabel() {
        termEndDate.setText(simpleDateFormat.format(calendarEndDate.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.termdeletemenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.delete:
                for (TermsEntity term : repository.getAllTerms()) {
                    if (term.getTermID() == termID) currentTerm = term;
                }

                numCourses = 0;
                for (CoursesEntity courses : repository.getAllCourses()) {
                    if (courses.getTermID() == termID) ++numCourses;
                }

                if (numCourses == 0) {
                    repository.delete(currentTerm);
                    Toast.makeText(TermDetailsActivity.this, "Term " + termName + " Was Deleted.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(TermDetailsActivity.this, "Can't Delete a Term with Courses.", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return true;
    }

    public void saveTermDetails(View view) {
        TermsEntity termsEntity;
        if (termID == -1) {
            int termID=1;
            if (repository.getAllTerms().size()!=0)
                termID = (repository.getAllTerms().size() +1);
            termsEntity = new TermsEntity(termID, termNameText.getText().toString(), termStartDate.getText().toString(), termEndDate.getText().toString());
            repository.insert(termsEntity);
        }
        else {
        termsEntity = new TermsEntity(termID, termNameText.getText().toString(), termStartDate.getText().toString(), termEndDate.getText().toString());
        repository.update(termsEntity);
        }
        this.finish();
    }

    public void addCourseToTerm(View view) {
        saveTermDetails(view);
        if (termID == -1) {
            Toast.makeText(this, "Please Save Term Before Adding Courses", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(TermDetailsActivity.this, CourseDetailsActivity.class);
            intent.putExtra("termID", termID);
            startActivity(intent);
        }
    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.associatedCourseListView);
        Repository repo = new Repository(getApplication());
        List<CoursesEntity> courses = repo.getAllTermCourses(termID);
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(courses);
    }
}