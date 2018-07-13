package com.example.menes.introtointros;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TextView numberPane,tab2TextView;
    private Button incrementButton, buttonTab2;

    private int g =1 ,l = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(new Tab1Fragment(), "Tab 1");
        adapter.addFragment(new Tab2Fragment(), "Tab 2");
        adapter.addFragment(new Tab3Fragment(), "Tab 3");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        highLightCurrentTab(0); // for initial selected tab view

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                highLightCurrentTab(position); // for tab change

                switch(position) {
                    case 0:
                        numberPane = findViewById(R.id.numberPane);
                        incrementButton = findViewById(R.id.incrementButton);

                        incrementButton.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                numberPane.setText("Number is clicked "+g+" times!");
                                g++;
                            }
                        });
                    case 1:
                        tab2TextView = findViewById(R.id.tab2TextView);
                        buttonTab2 = findViewById(R.id.buttonTab2);

                        buttonTab2.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                tab2TextView.setText("Number is clicked "+l+" times!");
                                l++;
                            }
                        });

                    case 2:

                }




            }

            @Override
            public void onPageScrollStateChanged(int state) {
              //  Toast.makeText(MainActivity.this,"Gotcha!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void highLightCurrentTab(int position) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            assert tab != null;
            tab.setCustomView(null);
            tab.setCustomView(adapter.getTabView(i));
        }

        TabLayout.Tab tab = tabLayout.getTabAt(position);
        assert tab != null;
        tab.setCustomView(null);
        tab.setCustomView(adapter.getSelectedTabView(position));
    }
}

/*
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


       tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
}*/
