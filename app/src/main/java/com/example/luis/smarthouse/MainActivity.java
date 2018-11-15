package com.example.luis.smarthouse;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private ActionBar actionBar;

    private TabLayout tabs;
    private ViewPager pager;

    private AdapterPager adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        tabs = (TabLayout) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);

        setSupportActionBar(toolbar);

        adapter = new AdapterPager(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setIcon(R.drawable.tab2);
        tabs.getTabAt(1).setIcon(R.drawable.tab3);
        tabs.getTabAt(2).setIcon(R.drawable.tab4);
        tabs.getTabAt(3).setIcon(R.drawable.tab5);
        tabs.getTabAt(4).setIcon(R.drawable.tab6);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Smart House");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.menu_info:
                i = new Intent(this, info.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class AdapterPager extends FragmentPagerAdapter {

        public AdapterPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "";
                case 1:
                    return "";
                case 2:
                    return "";
                case 3:
                    return "";
                case 4:
                    return "";
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    tab2 t2 = new tab2();
                    return t2;
                case 1:
                    tab3 t3 = new tab3();
                    return t3;
                case 2:
                    tab4 t4 = new tab4();
                    return t4;
                case 3:
                    tab5 t5 = new tab5();
                    return t5;
                case 4:
                    tab6 t6 = new tab6();
                    return t6;
            }

            return null;
        }
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


}
