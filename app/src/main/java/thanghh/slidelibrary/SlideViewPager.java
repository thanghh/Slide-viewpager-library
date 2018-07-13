package thanghh.slidelibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;




public class SlideViewPager extends LinearLayout {
    private ViewPager mViewPager;
    private TabLayout mTabDot;

    private ImagePagerAdapter mImagePagerAdapter;
    private int page = 0;

    public SlideViewPager(Context context) {
        this(context, null);
    }

    public SlideViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SlideViewPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SlideRecyclerView);
        boolean b = a.getBoolean(R.styleable.SlideRecyclerView_autoScroll, false);
        if (b) {
            try {
                autoScroll();
            } catch (Exception e) {
            }
        }
        init(getContext());
    }

    public void setListImage(List<Integer> listImage) {

        mImagePagerAdapter.addAll(listImage);
        for (int i = 0; i < listImage.size(); i++) {
            mTabDot.getTabAt(i).setIcon(R.drawable.seleted_tab_slide);
        }
        wrapTabIndicator(mTabDot);
    }


    private void init(Context context) {
        setOrientation(VERTICAL);

        mViewPager = new ViewPager(context);

        mImagePagerAdapter = new ImagePagerAdapter(context);

        LayoutParams mLayoutParamsImage = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelOffset(R.dimen.slide_market_height_192));
        mViewPager.setLayoutParams(mLayoutParamsImage);

        mViewPager.setAdapter(mImagePagerAdapter);

        mTabDot = new TabLayout(context);
        LayoutParams mLayoutParamsDot = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParamsDot.topMargin = getResources().getDimensionPixelOffset(R.dimen.margin_5);
        mLayoutParamsDot.gravity = Gravity.CENTER;
        mTabDot.setLayoutParams(mLayoutParamsDot);

        mTabDot.setupWithViewPager(mViewPager);

        mTabDot.setSelectedTabIndicatorHeight(0);
        mTabDot.setTabGravity(TabLayout.GRAVITY_CENTER);
        mTabDot.setTabMode(TabLayout.MODE_FIXED);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabDot.getTabAt(position).setIcon(R.drawable.seleted_tab_slide);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        addView(mViewPager);
        addView(mTabDot);

    }

    public void wrapTabIndicator(TabLayout tabLayout) {
        View tabStrip = tabLayout.getChildAt(0);
        if (tabStrip instanceof ViewGroup) {
            int childCount = ((ViewGroup) tabStrip).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View tabView = ((ViewGroup) tabStrip).getChildAt(i);
                tabView.setMinimumWidth(0);
                tabView.setPadding(0, tabView.getPaddingTop(), 0, tabView.getPaddingBottom());
            }
            tabLayout.requestLayout();
        }

    }

    private void autoScroll() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mImagePagerAdapter.getCount() == page) {
                            page = 0;
                        } else {
                            page++;
                        }
                        mViewPager.setCurrentItem(page, true);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(doAsynchronousTask, 3000, 3000);

    }

}
