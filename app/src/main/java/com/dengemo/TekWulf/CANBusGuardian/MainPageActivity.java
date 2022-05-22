package com.dengemo.TekWulf.CANBusGuardian;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.LinkedList;
import java.util.Objects;


public class MainPageActivity extends FragmentActivity /*implements AdapterView.OnItemClickListener*/ {

    UserFragment userFragment;

    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    MenuItem menuItem;

//    private static final LinkedList<StandardItem> itemList;
//    static {
//        itemList = new LinkedList<>();
//        itemList.addLast(new StandardItem("设置"));
//        itemList.addLast(new StandardItem("关于"));
//    }

    @SuppressLint({"ClickableViewAccessibility", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.item_home_page);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(1);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.item_home_page:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.item_alarm_information:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.item_voltage_fingerprint:
                            viewPager.setCurrentItem(2);
                            break;
                        case R.id.item_user:
                            viewPager.setCurrentItem(3);
                            break;
                    }
                    return false;
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        setupViewPager(viewPager);

        //initComponent();
    }

        //设置MainPageActivity中ViewPager内的Fragment
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(HomeFragment.newInstance("主页"));
        adapter.addFragment(AlarmInformationFragment.newInstance("告警信息"));
        adapter.addFragment(VoltageFingerprintFragment.newInstance("电压指纹"));
        adapter.addFragment(UserFragment.newInstance("用户"));

        viewPager.setAdapter(adapter);
    }

//        初始化ListView内所有的Item组件
//    private void initComponent() {
//        StandardItemAdaptor adapter = new StandardItemAdaptor(itemList, getApplicationContext());
//
//        userFragment = new UserFragment();
//
//        ListView listView = userFragment.requireView().findViewById(R.id.list_view);
//
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(this);
//    }


//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent();
//        StandardItem item = (StandardItem) parent.getItemAtPosition(position);
//        if ("设置".equals(item.getName())) {
//            intent.setClass(MainPageActivity.this, SettingActivity.class);
//        }else if("关于".equals(item.getName())) {
//            intent.setClass(MainPageActivity.this, AboutUsActivity.class);
//        }else {
//            Toast.makeText(getApplicationContext(), "功能暂未开放", Toast.LENGTH_LONG).show();
//            return;
//        }
//        startActivity(intent);
//    }
}

