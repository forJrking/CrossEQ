package com.king.crosseq;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.king.mylibrary.CrossView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mFl;
    private CrossView mCrossView;
    private FloatingActionButton mFab;
    private Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFl = (FrameLayout) findViewById(R.id.fl);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "X: " + mCrossView.getEQ()[0] + " Y: " + mCrossView.getEQ()[1], Snackbar.LENGTH_SHORT)
                        .show();//拿取当前点的坐标
            }
        });
        btn1 = (Button) findViewById(R.id.up);
        btn2 = (Button) findViewById(R.id.down);
        btn3 = (Button) findViewById(R.id.left);
        btn4 = (Button) findViewById(R.id.right);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        mCrossView = new CrossView(MainActivity.this);

//        mCrossView.init(mFl);//这个方法用于初始化到中间
        mCrossView.init(mFl, 100, 100);//回显的位置

        mFl.addView(mCrossView);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up:
                mCrossView.init(mCrossView.getEQ()[0], --mCrossView.getEQ()[1]);
                break;
            case R.id.down:
                mCrossView.init(mCrossView.getEQ()[0], ++mCrossView.getEQ()[1]);
                break;
            case R.id.left:
                mCrossView.init(--mCrossView.getEQ()[0], mCrossView.getEQ()[1]);
                break;
            case R.id.right:
                mCrossView.init(++mCrossView.getEQ()[0], mCrossView.getEQ()[1]);
                break;
            default:
                break;
        }
    }
}
