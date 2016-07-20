package com.example.hzg.android_scrollview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private ScrollView scrollView;
    private Button up_btn;
    private Button down_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textView =(TextView) findViewById(R.id.textView);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        up_btn = (Button) findViewById(R.id.up);
        down_btn = (Button) findViewById(R.id.down);
        up_btn.setOnClickListener(this);
        down_btn.setOnClickListener(this);

        textView.setText(getResources().getString(R.string.content));
        //隐藏纵向ScrollBar
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
//                    case MotionEvent.ACTION_UP:{
//                        break;
//                    }
//                    case MotionEvent.ACTION_DOWN:{
//                        break;
//                    }
                    case MotionEvent.ACTION_MOVE: {
                        /**
                         * (1)getScrollY()————滚动条滑动的距离
                         * (2)getMeasuredHeight()
                         * (3)getHeight()
                         * getScrollY()+getHeight()==getMeasuredHeight()
                         */
                        //顶部状态
                        if (scrollView.getScrollY() <= 0) {
                            Log.i("Main", "滑动到顶部");
                        }
                        //底部状态
                        // TextView的总高度<=一屏幕的高度+滚动条的滚动距离
                        if (scrollView.getChildAt(0).getMeasuredHeight()<=
                                scrollView.getScrollY()+scrollView.getHeight()) {
                            Log.i("Main", "滑动到底部");
                            Log.i("Main",
                                    "scrollView.getChildAt(0).getMeasuredHeight()="
                                            + scrollView.getChildAt(0)
                                            .getMeasuredHeight()
                                            + "scrollView.getHeight()="
                                            + scrollView.getHeight()
                                            + "scrollView.getScrollY()="
                                            + scrollView.getScrollY());
                            //追加内容，永远滑不完
                            textView.append(getResources().getString(R.string.content));
                        }
                        break;
                    }
                }
                return false;
            }
        });
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            //scrollTo：以滚动视图起始位置开始计算的
            //scrollBy：相对前一次的位置，去滚动对应的距离

            case R.id.up:
            {
                scrollView.scrollBy(0, -30);
                break;
            }

            case R.id.down:
            {

                scrollView.scrollBy(0, 30);
                break;
            }
        }
    }

}
