package com.example.richardbrown_c196.Entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Assessments")
public class AssessmentsEntity {

    @PrimaryKey (autoGenerate = true)
    private int assessmentID;
    private int courseID;
    private String assessmentName;
    private String startDate;
    private String endDate;
    private String assessmentType;

    public AssessmentsEntity(int assessmentID, int courseID, String assessmentName, String startDate, String endDate, String assessmentType) {
        this.assessmentID = assessmentID;
        this.courseID = courseID;
        this.assessmentName = assessmentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assessmentType = assessmentType;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getAssessmentName() { return assessmentName;}

    public void setAssessmentName (String assessmentName) { this.assessmentName = assessmentName;}

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "AssessmentsEntity{" +
                "assessmentID=" + assessmentID +
                ", courseID=" + courseID +
                ", assessmentName=" + assessmentName +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", assessmentType='" + assessmentType + '\'' +
                '}';
    }
}
