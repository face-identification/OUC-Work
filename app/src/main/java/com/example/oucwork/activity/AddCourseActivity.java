package com.example.oucwork.activity;

/**
 *  添加课程界面
 */

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.oucwork.R;

import static com.example.oucwork.activity.LoginActivity.action_schedule;

public class AddCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcourse_layout);

        Toolbar toolbar_addCourse = (Toolbar) findViewById(R.id.toolbar_addCourse);
        String[] years = new String[]{"2017","2018","2019"};
        String[] terms = new String[]{"春季","夏季","秋季"};
        Spinner year_spinner = (Spinner) findViewById(R.id.year_spinner);
        Spinner term_spinner = (Spinner) findViewById(R.id.term_spinner);

        setSupportActionBar(toolbar_addCourse);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,years);
        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_spinner.setAdapter(year_adapter);
        ArrayAdapter<String> term_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,terms);
        term_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        term_spinner.setAdapter(term_adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(action_schedule);
                finish();
                break;
            default:
        }
        return true;
    }
}
