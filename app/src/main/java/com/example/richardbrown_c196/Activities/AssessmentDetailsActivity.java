package com.example.richardbrown_c196.Activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.richardbrown_c196.Entities.AssessmentsEntity;
import com.example.richardbrown_c196.R;
import androidx.appcompat.app.AppCompatActivity;

import com.example.richardbrown_c196.Database.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AssessmentDetailsActivity extends AppCompatActivity {

    EditText assessmentNameText;
    EditText assessmentStartDate;
    EditText assessmentEndDate;
    String assessmentName;
    String assessmentStart;
    String assessmentEnd;
    String dateFormat;
    String assessmentType;
    Spinner typeSpinner;
    Repository repository;
    SimpleDateFormat simpleDateFormat;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    AssessmentsEntity currentAssessment;
    final Calendar calendarStartDate = Calendar.getInstance();
    final Calendar calendarEndDate = Calendar.getInstance();
    int courseID = -1;
    int assessmentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
        assessmentNameText=findViewById(R.id.assessmentName);
        assessmentStartDate=findViewById(R.id.assessmentStartDate);
        assessmentEndDate=findViewById(R.id.assessmentEndDate);
        courseID=getIntent().getIntExtra("courseID", 0);
        assessmentID=getIntent().getIntExtra("id", -1);
        assessmentName=getIntent().getStringExtra("name");
        assessmentStart=getIntent().getStringExtra("startDate");
        assessmentEnd=getIntent().getStringExtra("endDate");
        assessmentType=getIntent().getStringExtra("type");
        assessmentNameText.setText(assessmentName);
        assessmentStartDate.setText(assessmentStart);
        assessmentEndDate.setText(assessmentEnd);
        repository = new Repository(getApplication());

        typeSpinner = (Spinner) findViewById(R.id.assessmentType);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,R.array.type_array,android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        typeSpinner.setAdapter(statusAdapter);

        dateFormat = "MM/dd/yy";
        simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

        assessmentStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String start = assessmentStartDate.getText().toString();
                if (start.equals("")) start = "02/22/2022";
                try {
                    calendarStartDate.setTime(simpleDateFormat.parse(start));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AssessmentDetailsActivity.this, startDate, calendarStartDate.get
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

        assessmentEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String end = assessmentEndDate.getText().toString();
                if (end.equals("")) end = "02/22/2022";
                try {
                    calendarEndDate.setTime(simpleDateFormat.parse(end));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AssessmentDetailsActivity.this, endDate, calendarEndDate.get
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
        assessmentStartDate.setText(simpleDateFormat.format(calendarStartDate.getTime()));
    }

    private void updateEndLabel() {
        assessmentEndDate.setText(simpleDateFormat.format(calendarEndDate.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assessmentnotifydelete, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.notifyAssessmentStart:
                //Deal with Start Date.
                String startDateFromScreen = assessmentStartDate.getText().toString();

                Date currentDate = null;
                try {
                    currentDate = simpleDateFormat.parse(startDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long trigger = currentDate.getTime();
                Intent intent = new Intent(AssessmentDetailsActivity.this, MyReceiver.class);
                intent.putExtra("key", "Assessment " + assessmentName + " is Available");
                PendingIntent pIntent = PendingIntent.getBroadcast(AssessmentDetailsActivity.this, MainActivity.numAlert++, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, pIntent);
                return true;

            case R.id.notifyAssessmentEnd:

                //Deal with End Date.
                String endDateFromScreen = assessmentEndDate.getText().toString();
                Date currentDate2 = null;

                try {
                    currentDate2 = simpleDateFormat.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Long trigger2 = currentDate2.getTime();
                Intent intent2 = new Intent(AssessmentDetailsActivity.this, MyReceiver.class);
                intent2.putExtra("key", "Assessment " + assessmentName + " is Due");
                PendingIntent pIntent2 = PendingIntent.getBroadcast(AssessmentDetailsActivity.this, MainActivity.numAlert++, intent2, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, pIntent2);
                return true;

            case R.id.delete:
                for (AssessmentsEntity assessment : repository.getAllAssessments()) {
                    if (assessment.getAssessmentID() == assessmentID)
                        currentAssessment = assessment;

                    if (currentAssessment == assessment) {
                        repository.delete(currentAssessment);
                        Toast.makeText(AssessmentDetailsActivity.this, "Assessment " + currentAssessment.getAssessmentName() + " Was Deleted.", Toast.LENGTH_LONG).show();
                    }
                }
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveAssessmentDetails(View view) {
        AssessmentsEntity assessmentsEntity;
        if (assessmentID == -1) {
            int newAssessmentID = (repository.getAllAssessments().size() +1);
            assessmentsEntity = new AssessmentsEntity(newAssessmentID, courseID, assessmentNameText.getText().toString(), assessmentStartDate.getText().toString(), assessmentEndDate.getText().toString(), typeSpinner.getSelectedItem().toString());
            repository.insert(assessmentsEntity);
        }
        else {
            assessmentsEntity = new AssessmentsEntity(assessmentID, courseID, assessmentNameText.getText().toString(), assessmentStartDate.getText().toString(), assessmentEndDate.getText().toString(), typeSpinner.getSelectedItem().toString());
            repository.update(assessmentsEntity);
        }
        this.finish();
    }
}