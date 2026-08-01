package com.example.richardbrown_c196.Entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Courses")
public class CoursesEntity {

    @PrimaryKey (autoGenerate = true)
    private int courseID;
    private int termID;
    private String courseName;
    private String startDate;
    private String endDate;
    private String courseNote;
    private String courseStatus;
    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;


    public CoursesEntity(int courseID, int termID, String courseName, String startDate, String endDate, String courseNote, String courseStatus, String mentorName, String mentorPhone, String mentorEmail) {
        this.courseID = courseID;
        this.termID = termID;
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseNote = courseNote;
        this.courseStatus = courseStatus;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {return courseName;
    }

    public void setCourseName(String courseName) {this.courseName = courseName;
    }

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

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    @Override
    public String toString() {
        return "CoursesEntity{" +
                "courseID=" + courseID +
                ", termID=" + termID +
                ", courseName=" + courseName +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", courseNote='" + courseNote + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", instructorName='" + mentorName + '\'' +
                ", instructorPhone='" + mentorPhone + '\'' +
                ", instructorEmail='" + mentorEmail + '\'' +
                '}';
    }
}
