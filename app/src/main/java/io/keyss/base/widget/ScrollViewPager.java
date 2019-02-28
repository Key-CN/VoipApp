package io.keyss.base.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Key
 * Time: 2019/02/20 20:06
 * Description: 默认不可滑动
 */
public class ScrollViewPager extends ViewPager {

    private boolean isScroll;

    /**
     * 设置滑动，默认禁止滑动就禁止动画过场
     * @param scroll false禁止滑动及动画
     */
    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }

    public ScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public ScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 是否拦截，在触摸处理之前
     * 拦截:会走到自己的onTouchEvent方法里面来
     * 不拦截:事件传递给子孩子
     */
    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //return false;//可行,不拦截事件,
        //return true;//不行,子控件无法处理事件
        //return super.onInterceptTouchEvent(ev);// 会有细微移动
        return isScroll && super.onInterceptTouchEvent(ev);
    }*/


    /**
     * 返回false继续传递，所以不用重写performClick相关操作
     * 触摸事件返回true时，表示消耗了事件，此时记得处理performClick相关
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //return false;// 可行,不消费,传给父控件
        //return true;// 可行,消费,拦截事件
        return isScroll && super.onTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, isScroll);
    }
}
