package com.example.dell.big_wanandroid.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.dell.big_wanandroid.R;

import butterknife.OnClick;
import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * Created by Dell on 2019/5/7.
 */

public class RlvHideUtils {

    private static boolean isScroll;


    public static void Hide(RadioGroup radioGroup, RecyclerView recyclerView, FloatingActionButton floatingActionButton){


          recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
              @Override
              public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                  super.onScrollStateChanged(recyclerView, newState);
                  //重写该方法主要是判断recyclerview是否在滑动
                  //0停止 ，1,2都是滑动
                  if (newState == 0) {
                      isScroll = false;
                  } else {
                      isScroll = true;
                  }

              }

              @Override
              public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                  super.onScrolled(recyclerView, dx, dy);
                  //这个主要是recyclerview滑动时让tab定位的方法

               /*recyclerView : 当前滚动的view
dx : 水平滚动距离
dy : 垂直滚动距离
dx > 0 时为手指向左滚动,列表滚动显示右面的内容
dx < 0 时为手指向右滚动,列表滚动显示左面的内容
dy > 0 时为手指向上滚动,列表滚动显示下面的内容
dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/

                  if (isScroll) {
                      if (dy < 0) {
                          radioGroup.setVisibility(View.VISIBLE);
                          floatingActionButton.setVisibility(View.VISIBLE);
                      } else if (dy > 0) {
                          radioGroup.setVisibility(View.GONE);
                          floatingActionButton.setVisibility(View.GONE);
                      }
                  }
              }
          });

      }
    public static void MoveHide(VerticalTabLayout tabLayout,RadioGroup radioGroup, RecyclerView recyclerView, FloatingActionButton floatingActionButton){

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //重写该方法主要是判断recyclerview是否在滑动
                //0停止 ，1,2都是滑动
                if (newState == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                tabLayout.setTabSelected(top);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //这个主要是recyclerview滑动时让tab定位的方法

               /*recyclerView : 当前滚动的view
dx : 水平滚动距离
dy : 垂直滚动距离
dx > 0 时为手指向左滚动,列表滚动显示右面的内容
dx < 0 时为手指向右滚动,列表滚动显示左面的内容
dy > 0 时为手指向上滚动,列表滚动显示下面的内容
dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //可见的第一条条目
                int top = layoutManager.findFirstVisibleItemPosition();
                //可见的最后一条条目
                int bottom = layoutManager.findLastVisibleItemPosition();
                if (isScroll) {
                    if (dy < 0) {
                        radioGroup.setVisibility(View.VISIBLE);
                        floatingActionButton.setVisibility(View.VISIBLE);
                    } else if (dy > 0) {
                        tabLayout.setTabSelected(top);
                        radioGroup.setVisibility(View.GONE);
                        floatingActionButton.setVisibility(View.GONE);
                    }
                }

            }
        });

      }
    public static void TabHide(RadioGroup radioGroup, RecyclerView recyclerView, FloatingActionButton floatingActionButton){


          recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
              @Override
              public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                  super.onScrollStateChanged(recyclerView, newState);
                  //重写该方法主要是判断recyclerview是否在滑动
                  //0停止 ，1,2都是滑动
                  if (newState == 0) {
                      isScroll = false;
                  } else {
                      isScroll = true;
                  }

              }

              @Override
              public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                  super.onScrolled(recyclerView, dx, dy);
                  //这个主要是recyclerview滑动时让tab定位的方法

               /*recyclerView : 当前滚动的view
dx : 水平滚动距离
dy : 垂直滚动距离
dx > 0 时为手指向左滚动,列表滚动显示右面的内容
dx < 0 时为手指向右滚动,列表滚动显示左面的内容
dy > 0 时为手指向上滚动,列表滚动显示下面的内容
dy < 0 时为手指向下滚动,列表滚动显示上面的内容*/

                  if (isScroll) {
                      if (dy < 0) {
                          radioGroup.setVisibility(View.VISIBLE);
                      } else if (dy > 0) {
                          radioGroup.setVisibility(View.GONE);
                          floatingActionButton.setVisibility(View.GONE);
                      }
                  }
              }
          });

      }

      public static void OnClicks(FloatingActionButton floatingActionButton,RecyclerView recyclerView,Toolbar toolbar){
          floatingActionButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  recyclerView.smoothScrollToPosition(0);
                 // toolbar.setVisibility(View.VISIBLE);

              }
          });
      }
      public static void OnTabClicks(FloatingActionButton floatingActionButton,RecyclerView recyclerView,Toolbar toolbar,TabLayout tabLayout){
          floatingActionButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  recyclerView.smoothScrollToPosition(0);
                 // toolbar.setVisibility(View.VISIBLE);
                  tabLayout.setVisibility(View.VISIBLE);
              }
          });
      }

    @SuppressLint("ClickableViewAccessibility")
    public static void initRecy(RecyclerView recyclerView, Toolbar toolbar) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            public float mEndY;
            public float mStartY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mStartY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mEndY = event.getY();
                        float v1 = mEndY - mStartY;
                        if (v1 > 1) {

                            //我这个是在fragment中的操作 这个是获取activity中的布局
                          //  toolbar.setVisibility(View.VISIBLE);

                            //这个就是当前页面的头布局id
                            //.setVisibility(View.VISIBLE);
                        } else if (v1 < -1) {
                           //toolbar.setVisibility(View.GONE);
                        }
                        break;
                }
                return gestureDetector.onTouchEvent(event);
            }
        });
    }
    @SuppressLint("ClickableViewAccessibility")
    public static void initTabRecy(RecyclerView recyclerView, Toolbar toolbar,FloatingActionButton floatingActionButton) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            public float mEndY;
            public float mStartY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mStartY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mEndY = event.getY();
                        float v1 = mEndY - mStartY;
                        if (v1 > 1) {

                            //我这个是在fragment中的操作 这个是获取activity中的布局
                            //toolbar.setVisibility(View.VISIBLE);
//                            tabLayout.setVisibility(View.VISIBLE);
                            floatingActionButton.setVisibility(View.VISIBLE);

                            //这个就是当前页面的头布局id
                        } else if (v1 < -1) {
                          // toolbar.setVisibility(View.GONE);
//                           tabLayout.setVisibility(View.GONE);
                           floatingActionButton.setVisibility(View.GONE);
                        }
                        break;
                }
                return gestureDetector.onTouchEvent(event);
            }
        });
    }
    static GestureDetector gestureDetector = new GestureDetector(MyApp.getsMyApp(),
            new GestureDetector.OnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    return false;
                }

                @Override
                public void onShowPress(MotionEvent e) {
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    // do something
                    return true;
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    return false;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    return false;
                }
            });



}
