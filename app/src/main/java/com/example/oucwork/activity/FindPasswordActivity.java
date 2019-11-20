package com.example.oucwork.activity;

/*
* 找回密码界面
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

import static com.example.oucwork.activity.RegisterActivity.action_login;

public class FindPasswordActivity extends AppCompatActivity {
    static Intent action_resetPassword = new Intent("ACTION_RESETPASSWORD");
    private TextView otherWays;
    private TextView reGetCode;
    private EditText byPhone;
    private EditText reAuthCode;
    private Button nextButton;

    private String error1 = "请输入电话号码";
    private String error2 = "该用户不存在";
    private String error3 = "请输入验证码";
    private String error4 = "验证码错误，请重新输入";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpassword_layout);

        otherWays = (TextView) findViewById(R.id.otherWays);
        byPhone = (EditText) findViewById(R.id.byPhone);
        reGetCode = (TextView) findViewById(R.id.reGetCode);
        reAuthCode = (EditText) findViewById(R.id.reAuthCode);
        nextButton = (Button) findViewById(R.id.nextButton);
        Toolbar toolbar_findPassword = (Toolbar) findViewById(R.id.toolbar_findPassword);

        setSupportActionBar(toolbar_findPassword);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.back);

        /*其他方式*/
        otherWays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FindPasswordActivity.this,"此功能暂未实现",Toast.LENGTH_SHORT).show();
            }
        });

        /*获取验证码*/
        reGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyPhone()){
                    if (check_Phone()){
                        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(reGetCode, 60000, 1000);
                        countDownTimerUtils.start();
                        Toast.makeText(FindPasswordActivity.this,"验证码:1702003",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FindPasswordActivity.this,error2,Toast.LENGTH_SHORT).show();
                    }
                } else {
                    setShakeAnimation_phone();
                    Toast.makeText(FindPasswordActivity.this,error1,Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*进入下一步*/
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emptyAuthCode()){
                    if (check_authCode()){
                        startActivity(action_resetPassword);
                        finish();
                    } else {
                        Toast.makeText(FindPasswordActivity.this,error4,Toast.LENGTH_SHORT).show();
                    }
                } else {
                    setShakeAnimation_authCode();
                    Toast.makeText(FindPasswordActivity.this,error3,Toast.LENGTH_SHORT).show();
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
        if (reAuthCode.getText().toString().trim().isEmpty()){
            return true;
        }
        return false;
    }//判断验证码是否为空

    private boolean check_authCode() {
        if (reAuthCode.getText().toString().trim().equals("1702003")){
            return true;
        }
        return false;
    }//判断验证码是否正确

    private boolean check_Phone() {
        if (byPhone.getText().toString().trim().equals("12345678900")){
            return true;
        }
        return false;
    }//判断电话号码是否属于注册用户

    private boolean emptyPhone() {
        if (byPhone.getText().toString().trim().isEmpty()){
            return true;
        }
        return false;
    }//判断输入号码是否为空

    /*输入框晃动*/
    public void setShakeAnimation_phone(){
        byPhone.startAnimation(shakeAnimation(5));
    }

    public void setShakeAnimation_authCode(){
        reAuthCode.startAnimation(shakeAnimation(5));
    }

    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
