package com.example.oucwork.activity;

/*
* 个人资料修改界面
* */

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.oucwork.R;

import static com.example.oucwork.activity.LoginActivity.action_schedule;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        Toolbar toolbar_edit = (Toolbar) findViewById(R.id.toolbar_edit);
        Button edit_button = (Button) findViewById(R.id.edit_button);

        setSupportActionBar(toolbar_edit);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(action_schedule);
                finish();
            }
        });
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
