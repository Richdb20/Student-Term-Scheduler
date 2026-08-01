package com.example.richardbrown_c196.Activities;
import java.util.List;
import java.util.Objects;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.content.Intent;

import com.example.richardbrown_c196.Entities.CoursesEntity;
import com.example.richardbrown_c196.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.richardbrown_c196.Database.Repository;
import com.example.richardbrown_c196.Entities.TermsEntity;


public class TermListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView recyclerView=findViewById(R.id.termListView);
        Repository repo=new Repository(getApplication());
        List<TermsEntity> terms=repo.getAllTerms();
        final TermAdapter adapter=new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);
    }

        public boolean onCreateTermListMenu (Menu termMenu) {
            getMenuInflater().inflate(R.menu.menu_termslist, termMenu);
            return true;
        }

        public boolean onOptionsTermSelected (MenuItem termItem) {
        switch (termItem.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
            return super.onOptionsItemSelected(termItem);
    }

    public void addToTermList(View view) {
        Intent intent = new Intent(TermListActivity.this, TermDetailsActivity.class);
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.termListView);
        Repository repo = new Repository(getApplication());
        List<TermsEntity> terms = repo.getAllTerms();
        final TermAdapter adapter = new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);
    }
}