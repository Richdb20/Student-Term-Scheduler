package com.example.richardbrown_c196.DAO;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;
import com.example.richardbrown_c196.Entities.AssessmentsEntity;
import com.example.richardbrown_c196.Entities.CoursesEntity;


@Dao
public interface AssessmentDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AssessmentsEntity assessmentsEntity);

    @Update
    void update(AssessmentsEntity assessmentsEntity);

    @Delete
    void delete(AssessmentsEntity assessmentsEntity);

    @Query("SELECT * FROM Assessments ORDER BY AssessmentID ASC")
    List<AssessmentsEntity> getAllAssessments();

    @Query("SELECT * FROM Assessments WHERE courseID=:cID ORDER BY assessmentID ASC")
    List<AssessmentsEntity> getAllCourseAssessments(int cID);
}
