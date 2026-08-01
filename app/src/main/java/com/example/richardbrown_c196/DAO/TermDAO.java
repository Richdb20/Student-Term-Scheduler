package com.example.richardbrown_c196.DAO;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;
import com.example.richardbrown_c196.Entities.TermsEntity;


@Dao
public interface TermDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TermsEntity termsEntity);

    @Update
    void update(TermsEntity termsEntity);

    @Delete
    void delete(TermsEntity termsEntity);

    @Query("SELECT * FROM Terms ORDER BY termID ASC")
    List<TermsEntity> getAllTerms();
}
