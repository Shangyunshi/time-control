package com.shangyunshi.timecontrol;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.shangyunshi.timecontrol.db.TaskDao;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * public protected private
 *
 * class Person{
 * protected eyes;
 * private nose;
 *
 * walk(){ leg.walk()}
 *
 * }
 *
 * class Man extends Person{
 * protected eyes;
 *
 * walk(){ super.walk(); rest()}
 * }
 *
 * int add(int a,int b){ return a + b}
 *
 * Activity onCreate() onStart() onRestart() onResume() onPause() onStop() onDestroy()
 *
 * toolbar
 * HomeActivity -> BaseActivity -> AppCompatActivity
 * Act1 Act2
 *
 * class 类       人
 * instance 实例  ssy
 *
 * Person person
 *
 *
 * Java 变量
 * 成员变量 类所拥有的变量  类的任何地方使用 members
 * 局部变量 方法用的变量   该方法中使用
 *
 * 第一行代码 Android pdf
 * Java HeadFirst Java pdf
 * Java ThinkingInJava
 */
public class HomeActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFabBtn;
    private PagerAdapter mAdapter;
    private ListFragment[] mFragments = new ListFragment[2];
    private DatePickerDialog mDatePicker;
    
    private SimpleDateFormat mTextFormat;
    private SimpleDateFormat mDayFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mTextFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mDayFormat = new SimpleDateFormat("yyyy-MM-dd");

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mFabBtn = findViewById(R.id.fab);
        mFragments[0] = new ListFragment();
        mFragments[1] = new ListFragment();
        mViewPager.setAdapter(mAdapter = new MyAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                final TabLayout.Tab tab = mTabLayout.getTabAt(position);
                mTabLayout.selectTab(tab);
            }

            @Override public void onPageSelected(int position) {

            }

            @Override public void onPageScrollStateChanged(int state) {

            }
        });
        mFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Navigation.startAddTaskActivityForResult(HomeActivity.this);
            }
        });

        initDatePickerDialog();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_calendar) {
            if (mDatePicker != null) {
                mDatePicker.show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, 24, 0, 0);
        mDatePicker = new DatePickerDialog(this,
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    Calendar choosedCalendar = Calendar.getInstance();
                    choosedCalendar.set(year, month, dayOfMonth);
                    if (calendar.compareTo(choosedCalendar) < 0) {
                        Toast.makeText(HomeActivity.this, "please choose a past day",
                            Toast.LENGTH_SHORT).show();
                    } else {
                        final String date = mDayFormat.format(choosedCalendar.getTime());
                        Navigation.startTaskActivity(HomeActivity.this, date);
                    }
                }
            },
            year, month, day);
    }

    @Override protected void onSetupToolbar(Toolbar toolbar) {
        super.onSetupToolbar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
    }

    //fragment -> adapter -> viewpager
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 10003) {
            if (resultCode == RESULT_OK) {
                mAdapter = new MyAdapter(getSupportFragmentManager());
                mViewPager.setAdapter(mAdapter);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
