package com.example.richardbrown_c196.Activities;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import java.util.Calendar;
import android.widget.Toast;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.EditText;
import java.text.ParseException;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import android.widget.ArrayAdapter;
import android.app.DatePickerDialog;

import com.example.richardbrown_c196.Entities.TermsEntity;
import com.example.richardbrown_c196.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.richardbrown_c196.Entities.CoursesEntity;
import com.example.richardbrown_c196.Database.Repository;
import com.example.richardbrown_c196.Entities.AssessmentsEntity;


public class CourseDetailsActivity extends AppCompatActivity {

    String courseName;
    String courseStart;
    String courseEnd;
    String courseNote;
    String courseStatus;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    String dateFormat;
    EditText courseNameText;
    EditText courseStartDate;
    EditText courseEndDate;
    EditText courseNoteText;
    EditText instructorNameText;
    EditText instructorPhoneText;
    EditText instructorEmailText;
    Spinner statusSpinner;
    Repository repository;
    SimpleDateFormat simpleDateFormat;
    CoursesEntity currentCourse;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar calendarStartDate = Calendar.getInstance();
    final Calendar calendarEndDate = Calendar.getInstance();
    int courseID;
    int termID = -1;
    int assessmentID;
    int numAssessments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        assessmentID = getIntent().getIntExtra("id", -1);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.associatedAssessmentListView);
        Repository repo = new Repository(getApplication());
        List<AssessmentsEntity> assessments = repo.getAllCourseAssessments(assessmentID);
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);
        courseNameText = findViewById(R.id.courseName);
        courseStartDate = findViewById(R.id.courseStartDate);
        courseEndDate = findViewById(R.id.courseEndDate);
        courseNoteText = findViewById(R.id.courseNote);
        instructorNameText = findViewById(R.id.instructorName);
        instructorPhoneText = findViewById(R.id.instructorPhone);
        instructorEmailText = findViewById(R.id.instructorEmail);
        termID = getIntent().getIntExtra("termID", 0);
        courseID = getIntent().getIntExtra("id", -1);
        courseName = getIntent().getStringExtra("name");
        courseStart = getIntent().getStringExtra("startDate");
        courseEnd = getIntent().getStringExtra("endDate");
        courseNote = getIntent().getStringExtra("courseNote");
        courseStatus = getIntent().getStringExtra("status");
        instructorName = getIntent().getStringExtra("instructorName");
        instructorPhone = getIntent().getStringExtra("instructorPhone");
        instructorEmail = getIntent().getStringExtra("instructorEmail");
        courseNameText.setText(courseName);
        courseStartDate.setText(courseStart);
        courseEndDate.setText(courseEnd);
        courseNoteText.setText(courseNote);
        instructorNameText.setText(instructorName);
        instructorPhoneText.setText(instructorPhone);
        instructorEmailText.setText(instructorEmail);
        repository = new Repository(getApplication());

        statusSpinner = (Spinner) findViewById(R.id.courseStatus);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.status_array, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        statusSpinner.setAdapter(statusAdapter);

        dateFormat = "MM/dd/yy";
        simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

        courseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String start = courseStartDate.getText().toString();
                if (start.equals("")) start = "02/22/2022";
                try {
                    calendarStartDate.setTime(simpleDateFormat.parse(start));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetailsActivity.this, startDate, calendarStartDate.get
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

        courseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String end = courseEndDate.getText().toString();
                if (end.equals("")) end = "02/22/2022";
                try {
                    calendarEndDate.setTime(simpleDateFormat.parse(end));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetailsActivity.this, endDate, calendarEndDate.get
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
    courseStartDate.setText(simpleDateFormat.format(calendarStartDate.getTime()));
    }

    private void updateEndLabel() {
        courseEndDate.setText(simpleDateFormat.format(calendarEndDate.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sharenotifydeletemenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, courseNote);
                sendIntent.putExtra(Intent.EXTRA_TITLE, courseNote);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

            case R.id.notifyCourseStart:
                //Deal with Start Date.
                String startDateFromScreen = courseStartDate.getText().toString();

                Date currentDate = null;
                try {
                    currentDate = simpleDateFormat.parse(startDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long trigger = currentDate.getTime();
                Intent intent = new Intent(CourseDetailsActivity.this, MyReceiver.class);
                intent.putExtra("key", "Course " + courseName + " Has Started");
                PendingIntent pIntent = PendingIntent.getBroadcast(CourseDetailsActivity.this, MainActivity.numAlert++, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, pIntent);
                return true;

            case R.id.notifyCourseEnd:
                //Deal with End Date.
                String endDateFromScreen = courseEndDate.getText().toString();
                Date currentDate2 = null;
                try {
                    currentDate2 = simpleDateFormat.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long trigger2 = currentDate2.getTime();
                Intent intent2 = new Intent(CourseDetailsActivity.this, MyReceiver.class);
                intent2.putExtra("key", "Course " + courseName + " Has Ended");
                PendingIntent pIntent2 = PendingIntent.getBroadcast(CourseDetailsActivity.this, MainActivity.numAlert++, intent2, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, pIntent2);
                return true;

            case R.id.delete:
                for (CoursesEntity course : repository.getAllCourses()) {
                    if (course.getCourseID() == courseID) currentCourse = course;
                }

                numAssessments = 0;
                for (AssessmentsEntity assessments : repository.getAllAssessments()) {
                    if (assessments.getCourseID() == courseID) ++numAssessments;
                }

                if (numAssessments == 0) {
                    repository.delete(currentCourse);
                    Toast.makeText(CourseDetailsActivity.this, "Course " + currentCourse.getCourseName() + " Was Deleted.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CourseDetailsActivity.this, "Can't Delete a Course with Assessments.", Toast.LENGTH_LONG).show();
                }
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveCourseDetails(View view) {
        CoursesEntity coursesEntity;
        if (courseID == -1) {
            courseID = (repository.getAllCourses().size() +1);
            coursesEntity = new CoursesEntity(courseID, termID, courseNameText.getText().toString(), courseStartDate.getText().toString(), courseEndDate.getText().toString(),courseNoteText.getText().toString(), statusSpinner.getSelectedItem().toString(),
                    instructorNameText.getText().toString(), instructorPhoneText.getText().toString(), instructorEmailText.getText().toString());
                    repository.insert(coursesEntity);
        }
        else {
            coursesEntity = new CoursesEntity(courseID, termID, courseNameText.getText().toString(), courseStartDate.getText().toString(), courseEndDate.getText().toString(),courseNoteText.getText().toString(), statusSpinner.getSelectedItem().toString(),
                    instructorNameText.getText().toString(), instructorPhoneText.getText().toString(), instructorEmailText.getText().toString());
                    repository.update(coursesEntity);
        }
    }

    public void addAssessmentToCourse(View view) {
        saveCourseDetails(view);
        if (courseID == -1) {
            Toast.makeText(this, "Please Save Course Before Adding Assessments", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(CourseDetailsActivity.this, AssessmentDetailsActivity.class);
            intent.putExtra("courseID", courseID);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.associatedAssessmentListView);
        Repository repo = new Repository(getApplication());
        List<AssessmentsEntity> assessments = repo.getAllCourseAssessments(assessmentID);
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);
    }
}