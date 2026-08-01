package com.example.richardbrown_c196.DAO;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;
import com.example.richardbrown_c196.Entities.CoursesEntity;


@Dao
public interface CourseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CoursesEntity coursesEntity);

    @Update
    void update(CoursesEntity coursesEntity);

    @Delete
    void delete(CoursesEntity coursesEntity);

    @Query("SELECT * FROM Courses ORDER BY courseID ASC")
    List<CoursesEntity> getAllCourses();

    @Query("SELECT * FROM Courses WHERE termID=:tID ORDER BY courseID ASC")
    List<CoursesEntity> getAllTermCourses(int tID);
}
