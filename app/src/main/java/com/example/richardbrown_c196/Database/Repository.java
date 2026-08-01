package com.example.richardbrown_c196.Database;
import java.util.List;
import android.app.Application;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import com.example.richardbrown_c196.DAO.AssessmentDAO;
import com.example.richardbrown_c196.DAO.CourseDAO;
import com.example.richardbrown_c196.DAO.TermDAO;
import com.example.richardbrown_c196.Entities.AssessmentsEntity;
import com.example.richardbrown_c196.Entities.CoursesEntity;
import com.example.richardbrown_c196.Entities.TermsEntity;


public class Repository {

    private TermDAO mTermDAO;
    private CourseDAO mCourseDAO;
    private AssessmentDAO mAssessmentDAO;
    private List<TermsEntity> mAllTerms;
    private List<CoursesEntity> mAllCourses;
    private List<AssessmentsEntity> mAllAssessments;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService dbExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository (Application application) {
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);

        mTermDAO=db.termDAO();
        mCourseDAO=db.courseDAO();
        mAssessmentDAO=db.assessmentDAO();
    }

    public void insert (TermsEntity termsEntity) {
        dbExecutor.execute(()->{mTermDAO.insert(termsEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insert (CoursesEntity coursesEntity) {
        dbExecutor.execute(()->{mCourseDAO.insert(coursesEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insert (AssessmentsEntity assessmentsEntity) {
        dbExecutor.execute(()->{mAssessmentDAO.insert(assessmentsEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update (TermsEntity termsEntity) {
        dbExecutor.execute(()->{mTermDAO.update(termsEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update (CoursesEntity coursesEntity) {
        dbExecutor.execute(()->{mCourseDAO.update(coursesEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update (AssessmentsEntity assessmentsEntity) {
        dbExecutor.execute(()->{mAssessmentDAO.update(assessmentsEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete (TermsEntity termsEntity) {
        dbExecutor.execute(()->{mTermDAO.delete(termsEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete (CoursesEntity coursesEntity) {
        dbExecutor.execute(()->{mCourseDAO.delete(coursesEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete (AssessmentsEntity assessmentsEntity) {
        dbExecutor.execute(()->{mAssessmentDAO.delete(assessmentsEntity);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<TermsEntity> getAllTerms() {
        dbExecutor.execute(() -> {
            mAllTerms = mTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public List<CoursesEntity> getAllCourses() {
        dbExecutor.execute(() -> {
            mAllCourses = mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public List<AssessmentsEntity> getAllAssessments() {
        dbExecutor.execute(() -> {
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public List<CoursesEntity> getAllTermCourses(int tID) {
        dbExecutor.execute(() -> {
            mAllCourses = mCourseDAO.getAllTermCourses(tID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public List<AssessmentsEntity> getAllCourseAssessments(int aID) {
        dbExecutor.execute(() -> {
            mAllAssessments = mAssessmentDAO.getAllCourseAssessments(aID);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }
}


