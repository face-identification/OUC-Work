package com.example.oucwork.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.oucwork.R;

import static com.example.oucwork.activity.LoginActivity.action_schedule;

public class SettingActivity extends AppCompatActivity {
    static Intent action_resetpassword = new Intent("ACTION_RESETPASSWORD");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        Toolbar toolbar_setting = (Toolbar) findViewById(R.id.toolbar_setting);
        TextView resetPassword = (TextView) findViewById(R.id.resetPassword);

        setSupportActionBar(toolbar_setting);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        /*重置密码*/
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(action_resetpassword);
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
