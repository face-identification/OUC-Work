package com.example.oucwork.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oucwork.R;

import static com.example.oucwork.activity.LoginActivity.action_schedule;
import static com.example.oucwork.activity.LoginActivity.user;
import static com.example.oucwork.activity.RegisterActivity.action_login;

/*重置密码活动*/

public class ResetPasswordActivity extends AppCompatActivity {
    private TextView userId;
    private EditText resetPassword;
    private Button resetButton;

    private String error1 = "请输入重置密码";
    private String right = "密码重置成功，请重新登录";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpassword_layout);

        userId = (TextView) findViewById(R.id.userId);
        resetPassword = (EditText) findViewById(R.id.resetPassword);
        resetButton = (Button) findViewById(R.id.resetButton);
        Toolbar toolbar_resetPassword = (Toolbar) findViewById(R.id.toolbar_resetPassword);

        setSupportActionBar(toolbar_resetPassword);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        userId.setText("17020031118");

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyPassword()){
                    Toast.makeText(ResetPasswordActivity.this,right,Toast.LENGTH_SHORT).show();
                    user.setPassword(resetPassword.getText().toString());
                    user.setId(-1);
                    startActivity(action_login);
                    finish();
                } else {
                    setShakeAnimation_password();
                    Toast.makeText(ResetPasswordActivity.this,error1,Toast.LENGTH_SHORT).show();
                }
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

    private boolean emptyPassword() {
        if (resetPassword.getText().toString().trim().isEmpty()){
            return true;
        }
        return false;
    }//判断重置密码是否为空

    /*输入框晃动*/
    public void setShakeAnimation_password(){
        resetPassword.startAnimation(shakeAnimation(5));
    }

    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
