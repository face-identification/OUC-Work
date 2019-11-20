package com.example.oucwork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oucwork.R;

import static com.example.oucwork.activity.LoginActivity.action_schedule;
import static com.example.oucwork.activity.LoginActivity.user;
import static com.example.oucwork.activity.RegisterActivity.action_login;

public class IdentityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identity_layout);

        ifOnLogin();

        ImageView student = (ImageView) findViewById(R.id.student);
        ImageView teacher = (ImageView) findViewById(R.id.teacher);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IdentityActivity.this, "欢迎学生使用OUC课堂学习",Toast.LENGTH_SHORT).show();
                user.setId(-1);
                user.setIdentity("student");
                startActivity(action_login);
                finish();
            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IdentityActivity.this, "欢迎教师使用OUC课堂教学",Toast.LENGTH_SHORT).show();
                user.setId(-1);
                user.setIdentity("teacher");
                startActivity(action_login);
                finish();
            }
        });
    }

    /*判断是否已经登录*/
    private void ifOnLogin() {
        if (user.getId() != 0 && user.getId() != -1){
            startActivity(action_schedule);
            finish();
        }
    }
}
