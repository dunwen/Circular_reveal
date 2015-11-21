package edu.cqut.cn.circular_reveal;

import android.animation.Animator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {


    private FrameLayout mFrameLayout;
    private FloatingActionButton mFAB;
    private Interpolator mInterpolator;
    private Fragment mFragment;
    private Animator circularAnimator;
    private float finalRadius = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFrameLayout = (FrameLayout) findViewById(R.id.frame_container);
        mFAB = (FloatingActionButton) findViewById(R.id.fab);
        mInterpolator = new FastOutLinearInInterpolator();

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FABclick(v);
            }
        });

    }


    @Override
    public void onBackPressed() {

        if(mFragment!=null){
            closeFragment();
        }else{
            super.onBackPressed();
        }
    }

    private void closeFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(mFragment);
        ft.commit();
//        fm.popBackStack();
        mFragment = null;
        mFrameLayout.setVisibility(View.INVISIBLE);
        mFAB.animate().scaleX(1).scaleY(1).setListener(null).start();

    }

    private void FABclick(View fabButton) {
        setupFragment(fabButton);
    }

    private void setupFragment(View fabButton) {
        mFragment = Content_Fragment.getContent_Fragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(mFrameLayout.getId(),mFragment);
        ft.commit();
        setAnimation(fabButton, mFrameLayout);



    }

    private void setAnimation(View view, final FrameLayout FrameLayout) {
        prepare(view, mFrameLayout);

        mFAB.animate()
                .scaleX(0)
                .scaleY(0)
                .setDuration(200)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        FrameLayout.setVisibility(View.VISIBLE);

                        circularAnimator.start();

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();

    }

    private void prepare(View view, FrameLayout FrameLayout) {
        int centerX = (view.getLeft()+view.getRight())/2;
        int centerY = (view.getTop()+view.getBottom())/2;
        finalRadius =(float) Math.hypot((double)centerX,(double)centerY);

        circularAnimator = ViewAnimationUtils.createCircularReveal(FrameLayout,centerX,centerY,0,finalRadius);
        circularAnimator.setDuration(500);

    }
}
