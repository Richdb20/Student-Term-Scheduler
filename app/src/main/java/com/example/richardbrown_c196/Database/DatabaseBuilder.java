package com.example.richardbrown_c196.Database;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.richardbrown_c196.DAO.AssessmentDAO;
import com.example.richardbrown_c196.DAO.CourseDAO;
import com.example.richardbrown_c196.DAO.TermDAO;
import com.example.richardbrown_c196.Entities.AssessmentsEntity;
import com.example.richardbrown_c196.Entities.CoursesEntity;
import com.example.richardbrown_c196.Entities.TermsEntity;


@Database(entities = {TermsEntity.class, CoursesEntity.class, AssessmentsEntity.class}, version = 13)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();

    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabase (final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "myDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
