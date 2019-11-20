package com.example.oucwork.activity;

/*
*  登录界面
* */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.oucwork.po.Login;

public class LoginActivity extends AppCompatActivity {
    static Intent action_schedule = new Intent("ACTION_SCHEDULE");
    static Intent action_identity = new Intent("ACTION_IDENTITY");
    static Login user = new Login();

    private EditText loginNumber;
    private EditText loginPassword;
    private TextView register;
    private TextView findPassword;
    private Button loginButton;

    private String error1 = "学号/工号不能为空";
    private String error2 = "密码不能为空";
    private String error3 = "学号/工号错误";
    private String error4 = "密码错误";
    private String right = "开始我们愉快的学习吧!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        loginNumber = (EditText) findViewById(R.id.loginNumber);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        register = (TextView) findViewById(R.id.register);
        findPassword = (TextView) findViewById(R.id.findPassword);
        Toolbar toolbar_login = (Toolbar) findViewById(R.id.toolbar_login);

        /*设置toolbar*/
        setSupportActionBar(toolbar_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        /*是否为注销后跳转*/
        if (user.getId() == 0){ // 已注销
            loginNumber.setText(user.getNumber());
            loginPassword.setText(user.getPassword());
        }

        /*注册*/
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent action_register = new Intent("ACTION_REGISTER");
                startActivity(action_register);
                finish();
            }
        });

        /*忘记密码*/
        findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent action_findPassword = new Intent("ACTION_FINDPASSWORD");
                startActivity(action_findPassword);
                finish();
            }
        });

        /*登录*/
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emptyNumber()){
                    if ((!emptyPassword())){
                        if (rightNumber()){
                            if(rightPassword()){
                                setUser();
                                Toast.makeText(LoginActivity.this,right,Toast.LENGTH_SHORT).show();
                                startActivity(action_schedule);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this,error4,Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this,error3,Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        setShakeAnimation_password();
                        Toast.makeText(LoginActivity.this,error2,Toast.LENGTH_SHORT).show();
                    }
                } else {
                    setShakeAnimation_number();
                    Toast.makeText(LoginActivity.this,error1,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*存入用户信息*/
    private void setUser() {
        user.setId(1);
        user.setNumber(loginNumber.getText().toString());
        user.setPassword(loginPassword.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(action_identity);
                finish();
                break;
            default:
        }
        return true;
    }

    private boolean rightPassword() {
        if ("17020031118".equals(loginPassword.getText().toString().trim())){
            return true;
        }
        return false;
    }//判断密码是否正确

    private boolean rightNumber() {
        if("17020031118".equals(loginNumber.getText().toString().trim())){
            return true;
        }
        return false;
    }//判断学号/工号是否正确

    private boolean emptyPassword() {
        if(loginPassword.getText().toString().trim().isEmpty()){
            return true;
        }
        return  false;
    }//判断密码是否为空

    private boolean emptyNumber() {
        if(loginNumber.getText().toString().trim().isEmpty() ){
            return true;
        }
        return false;
    }//判断学号/工号是否为空

    /*输入框晃动*/
    public void setShakeAnimation_number() {
        loginNumber.startAnimation(shakeAnimation(5));
    }

    public void setShakeAnimation_password(){
        loginPassword.startAnimation(shakeAnimation(5));
    }

    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

}
