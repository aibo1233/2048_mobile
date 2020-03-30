package com.example.a2048_mobile;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

public class MainActivity extends Activity {
    Grid g;
    private TextView[][] txtTest;
    private boolean changed=false;
    private float moveX;
    private float moveY;
    private float pressX;
    private float pressY;


    private void show()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                //改变数字
                int nb=g.Boxes[i][j].getNum();
                txtTest[i][j].setText(String.valueOf(nb));
                //动画效果
                AnimationSet animationSet = new AnimationSet(true);

                //参数1：x轴的初始值

                //参数2：x轴收缩后的值

                //参数3：y轴的初始值

                //参数4：y轴收缩后的值

                //参数5：确定x轴坐标的类型

                //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴

                //参数7：确定y轴坐标的类型

                //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴

                ScaleAnimation scaleAnimation = new ScaleAnimation(

                        1.0f, 1.08f,1.0f,1.08f,

                        Animation.RELATIVE_TO_SELF,0.5f,

                        Animation.RELATIVE_TO_SELF,0.5f);

                scaleAnimation.setDuration(80);

                animationSet.addAnimation(scaleAnimation);

                txtTest[i][j].startAnimation(animationSet);
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        //动画效果
                        AnimationSet animationSet = new AnimationSet(true);

                        //参数1：x轴的初始值

                        //参数2：x轴收缩后的值

                        //参数3：y轴的初始值

                        //参数4：y轴收缩后的值

                        //参数5：确定x轴坐标的类型

                        //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴

                        //参数7：确定y轴坐标的类型

                        //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴

                        ScaleAnimation scaleAnimation = new ScaleAnimation(

                                1.08f, 1.0f,1.08f,1.0f,

                                Animation.RELATIVE_TO_SELF,0.5f,

                                Animation.RELATIVE_TO_SELF,0.5f);

                        scaleAnimation.setDuration(80);

                        animationSet.addAnimation(scaleAnimation);

                        txtTest[i][j].startAnimation(animationSet);
                    }
                }
            }
        }, 80);//0.1秒后执行Runnable中的run方法

    }

    public void Reset(View view)
    {
        g.InitGrid();
        show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        g = new Grid(4, 4);
        g.InitGrid();
        txtTest=new TextView[4][4];
        txtTest[0][0] = (TextView)findViewById(R.id.textView0);
        txtTest[0][1] = (TextView)findViewById(R.id.textView1);
        txtTest[0][2] = (TextView)findViewById(R.id.textView2);
        txtTest[0][3] = (TextView)findViewById(R.id.textView3);
        txtTest[1][0] = (TextView)findViewById(R.id.textView4);
        txtTest[1][1] = (TextView)findViewById(R.id.textView5);
        txtTest[1][2] = (TextView)findViewById(R.id.textView6);
        txtTest[1][3] = (TextView)findViewById(R.id.textView7);
        txtTest[2][0] = (TextView)findViewById(R.id.textView8);
        txtTest[2][1] = (TextView)findViewById(R.id.textView9);
        txtTest[2][2] = (TextView)findViewById(R.id.textView10);
        txtTest[2][3] = (TextView)findViewById(R.id.textView11);
        txtTest[3][0] = (TextView)findViewById(R.id.textView12);
        txtTest[3][1] = (TextView)findViewById(R.id.textView13);
        txtTest[3][2] = (TextView)findViewById(R.id.textView14);
        txtTest[3][3] = (TextView)findViewById(R.id.textView15);
        show();

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                txtTest[i][j].setOnTouchListener(new OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            //按下
                            case MotionEvent.ACTION_DOWN:
                                pressX = event.getX();
                                pressY = event.getY();
                                break;
                            //移动
                            case MotionEvent.ACTION_MOVE:
                                moveX = event.getX();
                                moveY = event.getY();
                                break;
                            //松开
                            case MotionEvent.ACTION_UP:
                                if (moveX-pressX > 0 && Math.abs(moveY - pressY) <Math.abs(moveX - pressX)) {
                                    //Log.i("message", "向右");
                                    //Toast.makeText(MainActivity.this, "Right",Toast.LENGTH_SHORT).show();
                                    changed=g.Move_Right();
                                } else if (Math.abs(moveY - pressY) >Math.abs(moveX - pressX) && moveY - pressY < 0) {
                                    //Log.i("message", "向左");
                                    //Toast.makeText(MainActivity.this, "Up",Toast.LENGTH_SHORT).show();
                                    changed=g.Move_Up();
                                }else if(moveX-pressX < 0 && Math.abs(moveY - pressY) <Math.abs(moveX - pressX))
                                {
                                    //Toast.makeText(MainActivity.this, "Left",Toast.LENGTH_SHORT).show();
                                    changed=g.Move_Left();
                                }
                                else if(Math.abs(moveY - pressY) >Math.abs(moveX - pressX) && moveY - pressY > 0)
                                {
                                    //Toast.makeText(MainActivity.this, "Down",Toast.LENGTH_SHORT).show();
                                    changed=g.Move_Down();
                                }
                                else
                                {
                                    changed=false;
                                }
                                if(changed==true)
                                {
                                    show();
                                }
                                //判断游戏是否结束
                                g.CheckGameisOver();
                                if(g.isGameOver==true)
                                {
                                    Toast.makeText(MainActivity.this, "GameOver.",Toast.LENGTH_SHORT).show();
                                }
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
            }
        }

    }

}
