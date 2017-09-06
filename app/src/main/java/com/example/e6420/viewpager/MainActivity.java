package com.example.e6420.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pixplicity.multiviewpager.MultiViewPager;

public class MainActivity extends FragmentActivity {

    private float offset = -1;
    private float paddingLeft;
    private float minScale;
    private float minAlpha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MultiViewPager pager = (MultiViewPager) findViewById(R.id.pager);
        final TextView pagerPosition = (TextView)findViewById(R.id.tv_info);

        final FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 7;
            }

            @Override
            public Fragment getItem(int position) {
                return PageFragment.create(position);
            }

        };
        pager.setAdapter(adapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                pagerPosition.setText("View Pager Position "+(position+1));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pager.setPageTransformer(false, new ViewPager.PageTransformer() {

            public void transformPage(View page, float position) {
                if (offset == -1) {
                    offset = paddingLeft / page.getMeasuredWidth();
                }
                if (position < -1) {
                    page.setAlpha(0);
                } else if (position <= 1) {
                    float scaleFactor = Math.max(minScale, 1 - Math.abs(position - offset));
                    page.setScaleX(scaleFactor);
                    page.setScaleY(scaleFactor);
                    float alphaFactor = Math.max(minAlpha, 1 - Math.abs(position - offset));
                    page.setAlpha(alphaFactor);
                } else {
                    page.setAlpha(0);
                }
            }
        });
    }

}
