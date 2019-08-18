package com.shangyunshi.timecontrol;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * public protected private
 *
 * class Person{
 *     protected eyes;
 *     private nose;
 *
 *      walk(){ leg.walk()}
 *
 * }
 *
 * class Man extends Person{
 *     protected eyes;
 *
 *     walk(){ super.walk(); rest()}
 * }
 *
 * int add(int a,int b){ return a + b}
 *
 * Activity onCreate() onStart() onRestart() onResume() onPause() onStop() onDestroy()
 *
 *                  toolbar
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
 *  成员变量 类所拥有的变量  类的任何地方使用 members
 *  局部变量 方法用的变量   该方法中使用
 *
 * 第一行代码 Android pdf
 * Java HeadFirst Java pdf
 * Java ThinkingInJava
 *
 */
public class HomeActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private ListFragment[] mFragments = new ListFragment[2];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(getString(R.string.app_name));
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mFragments[0] = new ListFragment();
        mFragments[1] = new ListFragment();
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    //fragment -> adapter -> viewpager
    class MyAdapter extends FragmentPagerAdapter{

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
}
