package com.example.oucwork.activity;

/*
*  注册界面
* */

import android.content.Intent;
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
import com.example.oucwork.util.CountDownTimerUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.oucwork.activity.LoginActivity.action_schedule;

public class RegisterActivity extends AppCompatActivity {
    static Intent action_login = new Intent("ACTION_LOGIN");

    private EditText registerNumber;
    private EditText registerPassword;
    private EditText registerPhone;
    private EditText authCode;
    private TextView getCode;
    private Button registerButton;

    private String error1 = "学号/工号不能为空";
    private String error2 = "密码不能为空";
    private String error3 = "验证码错误，请重新获取验证码";
    private String error4 = "该学号/工号已存在,请直接登录";
    private String error5 = "请输入电话号码";
    private String error6 = "电话号码不存在";
    private String error7 = "请输入验证码";
    private String right = "注册成功!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        registerNumber = (EditText) findViewById(R.id.registerNumber);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerPhone = (EditText) findViewById(R.id.registerPhone);
        authCode = (EditText) findViewById(R.id.authCode);
        getCode = (TextView) findViewById(R.id.getCode);
        registerButton = (Button) findViewById(R.id.registerButton);
        Toolbar toolbar_register = (Toolbar) findViewById(R.id.toolbar_register);

        setSupportActionBar(toolbar_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        /*获取验证码*/
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyPhone()){
                    if(check_phone()){
                        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(getCode, 60000, 1000);
                        countDownTimerUtils.start();
                        Toast.makeText(RegisterActivity.this,"验证码:1702003",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this,error6,Toast.LENGTH_SHORT).show();
                    }
                } else {
                    setShakeAnimation_phone();
                    Toast.makeText(RegisterActivity.this,error5,Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*注册*/
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyAuthCode()){
                    if (!emptyNumber()){
                        if (!emptyPassword()){
                            if (check_authCode()){
                                if (check_number()){
                                    Toast.makeText(RegisterActivity.this,right,Toast.LENGTH_SHORT).show();
                                    startActivity(action_login);
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this,error4,Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this,error3,Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            setShakeAnimation_password();
                            Toast.makeText(RegisterActivity.this,error2,Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        setShakeAnimation_number();
                        Toast.makeText(RegisterActivity.this,error1,Toast.LENGTH_SHORT).show();
                    }
                } else {
                    setShakeAnimation_phone();
                    Toast.makeText(RegisterActivity.this,error7,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(action_login);
                finish();
                break;
            default:
        }
        return true;
    }

    private boolean emptyAuthCode() {
        if (authCode.getText().toString().trim().isEmpty()){
            return true;
        }
        return false;
    }//判断是否输入验证码

    private boolean emptyPhone() {
        if (authCode.getText().toString().trim().isEmpty()){
            return true;
        }
        return false;
    }//判断是否输入了电话号码

    private boolean check_phone() {
        Pattern pattern = null;
        Matcher matcher = null;
        pattern = Pattern.compile("^[1][3,5,8][0-9]{9}$"); // 验证手机号
        matcher = pattern.matcher(registerPhone.getText().toString().trim());
        if (matcher.matches() == true && registerPhone.getText().toString().trim().length() == 11){
            return true;
        }
        return false;
    }//判断输入是否为正确的电话号码

    private boolean check_number() {
        if (registerNumber.getText().toString().trim().equals("17020031118")){
            return false;
        }
        return true;
    }//判断该学号/工号是否已经注册

    private boolean check_authCode() {
        if (authCode.getText().toString().trim().equals("1702003")){
            return true;
        }
        return false;
    }//判断验证码是否正确

    private boolean emptyPassword() {
        if(registerPassword.getText().toString().trim().isEmpty()){
            return true;
        }
        return  false;
    }//判断密码是否为空

    private boolean emptyNumber() {
        if(registerNumber.getText().toString().trim().isEmpty() ){
            return true;
        }
        return false;
    }//判断学号/工号是否为空

    /*输入框晃动*/
    public void setShakeAnimation_number() {
        registerNumber.startAnimation(shakeAnimation(5));
    }

    public void setShakeAnimation_password(){
        registerPassword.startAnimation(shakeAnimation(5));
    }

    public void setShakeAnimation_phone(){
        authCode.startAnimation(shakeAnimation(5));
    }

    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
