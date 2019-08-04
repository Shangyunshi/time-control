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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

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
 *
 */
public class HomeActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager2;
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager2 = findViewById(R.id.view_pager);
        mViewPager2.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });

    }

}
