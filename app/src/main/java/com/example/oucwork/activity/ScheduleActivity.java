package com.example.oucwork.activity;

/*
*  课程表界面
* */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oucwork.R;
import com.example.oucwork.po.Course;

import java.util.ArrayList;

import static com.example.oucwork.activity.LoginActivity.user;
import static com.example.oucwork.activity.RegisterActivity.action_login;

public class ScheduleActivity extends AppCompatActivity {
    static Intent action_addCourse = new Intent("ACTION_ADDCOURSE");
    static Intent action_edit = new Intent("ACTION_EDIT");
    static Intent action_setting = new Intent("ACTION_SETTING");
    static Intent action_class = new Intent("ACTION_CLASS");

    private RelativeLayout day; // 星期几
    private String[] years = new String[]{"2017","2018","2019"};
    private String[] terms = new String[]{"春季","夏季","秋季"};
    int currentCoursesNumber = 0;
    int maxCoursesNumber = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_layout);

        Toolbar main_toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        ImageView head = (ImageView) findViewById(R.id.head);
        ImageView add = (ImageView) findViewById(R.id.add);
        ImageView header = (ImageView) findViewById(R.id.header);
        Spinner semester_spinner = (Spinner) findViewById(R.id.semester_spinner);
        Spinner season_spinner = (Spinner) findViewById(R.id.season_spinner);


        ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,years);
        year_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester_spinner.setAdapter(year_adapter);
        ArrayAdapter<String> term_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,terms);
        term_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        season_spinner.setAdapter(term_adapter);

        setSupportActionBar(main_toolbar);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(action_addCourse);
                finish();
            }
        });
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.edit:
                        startActivity(action_edit);
                        finish();
                        break;
                    case R.id.course:
                        Toast.makeText(ScheduleActivity.this,"我的课件",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.collection:
                        Toast.makeText(ScheduleActivity.this,"我的收藏",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.setting:
                        startActivity(action_setting);
                        finish();
                        break;
                    case R.id.out:
                        user.setId(0);
                        startActivity(action_login);
                        finish();
                        break;
                }
                return true;
            }
        });

        loadData();
        createLeftView();
    }

    // 加载数据
    private void loadData() {
        ArrayList<Course> courses = new ArrayList<>();
        // 任意创造两条数据
        Course course_1 = new Course(1, "2019", "秋季", "软件工程", "高峰", "6001", "Monday", 3, 4, 1, 17);
        Course course_2 = new Course(2, "2019", "秋季", "大职III", "赵凤娇", "信北众创空间", "Friday",5, 8, 1, 17);
        Course course_3 = new Course(3, "2019", "秋季", "操作系统", "林喜军", "7302", "Tuesday",5, 6, 1, 17);
        Course course_4 = new Course(4, "2019", "秋季", "操作系统", "林喜军", "7304", "Wednesday",5, 6, 1, 17);
        Course course_5 = new Course(5, "2019", "秋季", "计算机网络", "洪峰", "7110", "Wednesday",7, 8, 1, 17);
        courses.add(course_1);
        courses.add(course_2);
        courses.add(course_3);
        courses.add(course_4);
        courses.add(course_5);

        //加载课程表视图
        for (Course course:courses){
            createItemCourseView(course);
        }

    }

    // 创建左侧节数列表
    private void createLeftView() {
        for (int i = 0; i < maxCoursesNumber; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.schedule_left_view, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,180);
            view.setLayoutParams(params);

            TextView text = view.findViewById(R.id.class_number_text);
            text.setText(String.valueOf(++currentCoursesNumber));

            LinearLayout leftViewLayout = findViewById(R.id.left_view_layout);
            leftViewLayout.addView(view);
        }
    }

    // 创建单个课程视图
    private void createItemCourseView(final Course course) {
        String Day = course.getWeek();
        int dayId = 0;
        switch(Day) {
            case "Monday": dayId = R.id.Monday; break;
            case "Tuesday": dayId = R.id.Tuesday; break;
            case "Wednesday": dayId = R.id.Wednesday; break;
            case "Thursday": dayId = R.id.Thursday; break;
            case "Friday": dayId = R.id.Friday; break;
            case "Saturday": dayId = R.id.Saturday; break;
            case "Sunday": dayId = R.id.Sunday; break;
        }
        day = findViewById(dayId);

        int height = 180;
        final View v = LayoutInflater.from(this).inflate(R.layout.course_card, null); //加载单个课程布局
        v.setY(height * (course.getStart_time()-1)); //设置开始高度,即第几节课开始
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,(course.getEnd_time()-course.getStart_time()+1)*height - 8); //设置布局高度,即跨多少节课
        v.setLayoutParams(params);
        // 显示课程信息
        TextView lecture_name = v.findViewById(R.id.lecture_name);
        TextView lecture_teacher = v.findViewById(R.id.lecture_teacher);
        TextView lecture_site = v.findViewById(R.id.lecture_site);
        TextView lecture_time = v.findViewById(R.id.lecture_time);
        lecture_name.setText(course.getName());
        lecture_teacher.setText(course.getTeacher());
        lecture_site.setText(course.getSite());
        lecture_time.setText(course.getStart_week() + "-" + course.getEnd_time() + "周");
        day.addView(v);
        //长按删除课程
//        v.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                v.setVisibility(View.GONE);//先隐藏
//                day.removeView(v);//再移除课程视图
//                return true;
//            }
//        });
        // 点击卡片进入课堂
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(action_class,course.getId());
                finish();
            }
        });
    }
}
